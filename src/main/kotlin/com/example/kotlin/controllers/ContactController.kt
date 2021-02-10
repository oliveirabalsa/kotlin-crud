package com.example.kotlin.controllers

import com.example.kotlin.entities.Contact
import com.example.kotlin.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/contacts")
class ContactController {
    @Autowired
    lateinit var repository: ContactRepository

    @GetMapping
    fun index(): List<Contact> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Long): Optional<Contact> {
        return repository.findById(id)
    }

    @PostMapping
    fun create(@RequestBody contact: Contact): Contact {
        if(contact.name == "João Balsalobre") {
            contact.apply {
                this.name = "Meu pai"
            }
        }

        return repository.save(contact)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody newContact: Contact, @PathVariable("id") id: Long): Contact {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }
        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }
        return repository.save(contact)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }
        return repository.delete(contact)
    }
}