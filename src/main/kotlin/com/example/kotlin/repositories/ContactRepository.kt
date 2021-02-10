package com.example.kotlin.repositories

import com.example.kotlin.entities.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository: JpaRepository<Contact, Long> {
}