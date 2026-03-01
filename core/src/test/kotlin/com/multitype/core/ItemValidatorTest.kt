package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertEquals

class ItemValidatorTest {
    
    @Test
    fun test_create_not_null() {
        val validator = ItemValidatorBuilder.createNotNull()
        
        assertTrue(validator.isValid("test"))
        assertFalse(validator.isValid(null))
    }
    
    @Test
    fun test_create_not_blank() {
        val validator = ItemValidatorBuilder.createNotBlank()
        
        assertTrue(validator.isValid("hello"))
        assertFalse(validator.isValid(""))
    }
    
    @Test
    fun test_custom_validator() {
        val validator = ItemValidatorBuilder.create(
            validator = { it is String && it.length > 3 },
            errorMessage = { "String must have more than 3 characters" }
        )
        
        assertTrue(validator.isValid("hello"))
        assertFalse(validator.isValid("hi"))
        assertEquals("String must have more than 3 characters", validator.getErrorMessage("hi"))
    }
    
    @Test
    fun test_error_message_returns_null_for_valid() {
        val validator = ItemValidatorBuilder.createNotNull()
        
        assertEquals(null, validator.getErrorMessage("valid"))
    }
}
