package Practice1.hotelapp.service;

import Practice1.hotelapp.dto.CustomerRequestDTO;
import Practice1.hotelapp.dto.CustomerResponseDTO;
import Practice1.hotelapp.entity.Customer;
import Practice1.hotelapp.exception.ResourceNotFoundException;
import Practice1.hotelapp.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Customer not found with id " + id
                )
        );
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setCity(dto.getCity());

        Customer saved = customerRepository.save(customer);

        CustomerResponseDTO response = new CustomerResponseDTO();
        response.setId(saved.getCustomerId());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setEmail(saved.getEmail());
        response.setPhone(saved.getPhone());
        response.setCity(saved.getCity());

        return response;
    }

    public Page<Customer> getCustomers(
            String city,
            Pageable pageable
    ) {
        if (city != null && !city.isBlank()) {
            return customerRepository.findByCityIgnoreCase(city, pageable);
        }
        return customerRepository.findAll(pageable);
    }

}
