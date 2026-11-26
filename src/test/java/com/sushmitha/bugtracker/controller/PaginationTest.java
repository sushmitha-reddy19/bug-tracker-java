package com.sushmitha.bugtracker.controller;

import com.sushmitha.bugtracker.model.Bug;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc @WithMockUser
class PaginationTest {
    @Autowired MockMvc mvc;
    @Test void rejectsInvalidPageSize() throws Exception { mvc.perform(get("/api/bugs?page=-1")).andExpect(status().isBadRequest()); }
    @Test void acceptsEmptyPage() throws Exception { mvc.perform(get("/api/bugs?page=10&size=20")).andExpect(status().isOk()); }
}
