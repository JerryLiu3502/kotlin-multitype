package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class HeaderFooterHelperTest {
    
    @Test
    fun test_add_header() {
        val helper = HeaderFooterHelperBuilder.create()
        
        helper.addHeader("header")
        
        assertEquals(1, helper.getHeaderCount())
        assertTrue(helper.hasHeader())
    }
    
    @Test
    fun test_add_footer() {
        val helper = HeaderFooterHelperBuilder.create()
        
        helper.addFooter("footer")
        
        assertEquals(1, helper.getFooterCount())
        assertTrue(helper.hasFooter())
    }
    
    @Test
    fun test_remove_header() {
        val helper = HeaderFooterHelperBuilder.create()
        helper.addHeader("header")
        
        val removed = helper.removeHeader("header")
        
        assertTrue(removed)
        assertEquals(0, helper.getHeaderCount())
        assertFalse(helper.hasHeader())
    }
    
    @Test
    fun test_clear() {
        val helper = HeaderFooterHelperBuilder.create()
        helper.addHeader("header")
        helper.addFooter("footer")
        
        helper.clear()
        
        assertEquals(0, helper.getHeaderCount())
        assertEquals(0, helper.getFooterCount())
    }
    
    @Test
    fun test_get_headers() {
        val helper = HeaderFooterHelperBuilder.create()
        helper.addHeader("h1")
        helper.addHeader("h2")
        
        val headers = helper.getHeaders()
        
        assertEquals(2, headers.size)
    }
    
    @Test
    fun test_get_footers() {
        val helper = HeaderFooterHelperBuilder.create()
        helper.addFooter("f1")
        helper.addFooter("f2")
        
        val footers = helper.getFooters()
        
        assertEquals(2, footers.size)
    }
}
