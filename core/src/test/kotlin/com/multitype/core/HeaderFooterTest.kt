package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class HeaderFooterTest {
    
    @Test
    fun test_create_header() {
        val header = HeaderFooter.createHeader("Header Content")
        assertTrue(HeaderFooter.isHeader(header))
        assertFalse(HeaderFooter.isFooter(header))
        assertEquals("Header Content", header.content)
    }
    
    @Test
    fun test_create_footer() {
        val footer = HeaderFooter.createFooter("Footer Content")
        assertTrue(HeaderFooter.isFooter(footer))
        assertFalse(HeaderFooter.isHeader(footer))
        assertEquals("Footer Content", footer.content)
    }
    
    @Test
    fun test_is_header_or_footer() {
        val header = HeaderFooter.createHeader("Header")
        val footer = HeaderFooter.createFooter("Footer")
        val normalItem = "Normal Item"
        
        assertTrue(HeaderFooter.isHeaderOrFooter(header))
        assertTrue(HeaderFooter.isHeaderOrFooter(footer))
        assertFalse(HeaderFooter.isHeaderOrFooter(normalItem))
    }
    
    @Test
    fun test_header_equals() {
        val header1 = HeaderItem("Content")
        val header2 = HeaderItem("Content")
        val header3 = HeaderItem("Different")
        
        assertEquals(header1, header2)
        assertTrue(header1 != header3)
    }
    
    @Test
    fun test_footer_equals() {
        val footer1 = FooterItem("Content")
        val footer2 = FooterItem("Content")
        val footer3 = FooterItem("Different")
        
        assertEquals(footer1, footer2)
        assertTrue(footer1 != footer3)
    }
}
