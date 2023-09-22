//package com.andreea.librarium.controller;
//
//import com.andreea.librarium.controller.UtilizatorController;
//import com.andreea.librarium.model.Utilizatori;
//import com.andreea.librarium.repositories.UtilizatoriRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UtilizatorController.class)
//public class UtilizatorControllerTest {
//
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UtilizatoriRepository utilizatoriRepository;
//
//    @InjectMocks
//    private UtilizatorController utilizatorController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(utilizatorController).build();
//    }
//    @Test
//    void registerUserAccount() throws Exception {
//        Utilizatori utilizator = new Utilizatori();
//        utilizator.setNume("Marincas");
//        utilizator.setPrenume("Andreea");
//        utilizator.setVarsta(21);
//        utilizator.setTelefon("0745567654");
//        utilizator.setEmail("mandreea242@yahoo.com");
//        utilizator.setStrada("Lazuci");
//        utilizator.setOras("Baia Mare");
//        utilizator.setCodPostal("123456");
//        utilizator.setJudet("Maramures");
//        utilizator.setApartament("");
//        utilizator.setNumar("23");
//        utilizator.setScara("");
//        utilizator.setOcupatie("student");
//        utilizator.setParola("andreea23");
//        // Mock the repository save method
//        ArgumentCaptor<Utilizatori> utilizatorCaptor = ArgumentCaptor.forClass(Utilizatori.class);
//        when(utilizatoriRepository.save(utilizatorCaptor.capture())).thenReturn(utilizator);
//
//        mockMvc.perform(post("/register")
//                        .param("nume", utilizator.getNume())
//                        .param("prenume", utilizator.getPrenume())
//                        .param("varsta", utilizator.getVarsta().toString())
//                        .param("telefon", utilizator.getTelefon())
//                        .param("email", utilizator.getEmail())
//                        .param("strada", utilizator.getStrada())
//                        .param("oras", utilizator.getOras())
//                        .param("codPostal", utilizator.getCodPostal())
//                        .param("judet", utilizator.getJudet())
//                        .param("apartament", utilizator.getApartament())
//                        .param("numar", utilizator.getNumar())
//                        .param("scara", utilizator.getScara())
//                        .param("ocupatie", utilizator.getOcupatie())
//                        .param("parola", utilizator.getParola()))
//                .andExpect(status().isOk());
//
//        // Verify the captor
//        verify(utilizatoriRepository).save(any(Utilizatori.class));
//        Utilizatori capturedUtilizator = utilizatorCaptor.getValue();
//        // Compare the properties of capturedUtilizator with the original utilizator
//
//    }
//
//    @Test
//    void processLoginForm() throws Exception {
//        Utilizatori utilizator = new Utilizatori();
//        utilizator.setEmail("mandreea242@yahoo.com");
//        utilizator.setParola("andreea23");
//        // Mock the repository save method
//        ArgumentCaptor<Utilizatori> utilizatorCaptor = ArgumentCaptor.forClass(Utilizatori.class);
//        when(utilizatoriRepository.save(utilizatorCaptor.capture())).thenReturn(utilizator);
//
//        mockMvc.perform(post("/login")
//                        .param("email", utilizator.getEmail())
//                        .param("parola", utilizator.getParola()))
//                .andExpect(status().isOk());
//
//        // Verify the captor
//        verify(utilizatoriRepository).save(any(Utilizatori.class));
//        Utilizatori capturedUtilizator = utilizatorCaptor.getValue();
//        // Compare the properties of capturedUtilizator with the original utilizator
//
//    }
//
//
//    public static String toJson(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}