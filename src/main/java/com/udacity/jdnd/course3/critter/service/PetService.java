package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        List<Pet> pets = getPetsByCustomerId(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
        return pet;
    }
    public Pet getPetById(long petId){
        Pet pet = petRepository.getOne(petId);
        return pet;
    }
    public List<Pet> getAllPets(){
        List<Pet> pets = petRepository.findAll();
        return pets;
    }
    public List<Pet> getPetsByCustomerId(long customerId) {
        List<Pet> pets = petRepository.findPetByCustomerId(customerId);
        return pets;
    }
}
