package ru.practicum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainStatsClientTest {
    /**
     * Method under test: {@link MainStatsClient#get(String, Map)}
     */
    @Test
    void testGet() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualGetResult = mainStatsClient.get("Path", new HashMap<>());
        assertNull(actualGetResult.getBody());
        assertEquals(HttpStatus.CONTINUE, actualGetResult.getStatusCode());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#get(String, Map)}
     */
    @Test
    void testGet2() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenReturn(new ResponseEntity<>("Body", HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualGetResult = mainStatsClient.get("Path", new HashMap<>());
        assertEquals("Body", actualGetResult.getBody());
        assertEquals(HttpStatus.CONTINUE, actualGetResult.getStatusCode());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#get(String, Map)}
     */
    @Test
    void testGet3() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any())).thenReturn(responseEntity);
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        assertSame(responseEntity, mainStatsClient.get("Path", new HashMap<>()));
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#get(String, Map)}
     */
    @Test
    void testGet4() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualGetResult = mainStatsClient.get("Path", new HashMap<>());
        assertTrue(actualGetResult.hasBody());
        assertEquals(HttpStatus.CONTINUE, actualGetResult.getStatusCode());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#post(String, Map, Object)}
     */
    @Test
    void testPost() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualPostResult = mainStatsClient.post("Path", new HashMap<>(), "Body");
        assertNull(actualPostResult.getBody());
        assertEquals(HttpStatus.CONTINUE, actualPostResult.getStatusCode());
        assertTrue(actualPostResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#post(String, Map, Object)}
     */
    @Test
    void testPost2() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenReturn(new ResponseEntity<>("Body", HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualPostResult = mainStatsClient.post("Path", new HashMap<>(), "Body");
        assertEquals("Body", actualPostResult.getBody());
        assertEquals(HttpStatus.CONTINUE, actualPostResult.getStatusCode());
        assertTrue(actualPostResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#post(String, Map, Object)}
     */
    @Test
    void testPost3() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any())).thenReturn(responseEntity);
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        assertSame(responseEntity, mainStatsClient.post("Path", new HashMap<>(), "Body"));
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }

    /**
     * Method under test: {@link MainStatsClient#post(String, Map, Object)}
     */
    @Test
    void testPost4() throws RestClientException {
        RestTemplate rest = mock(RestTemplate.class);
        when(rest.exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        MainStatsClient mainStatsClient = new MainStatsClient(rest);
        ResponseEntity<Object> actualPostResult = mainStatsClient.post("Path", new HashMap<>(), "Body");
        assertTrue(actualPostResult.hasBody());
        assertEquals(HttpStatus.CONTINUE, actualPostResult.getStatusCode());
        assertTrue(actualPostResult.getHeaders().isEmpty());
        verify(rest).exchange(Mockito.<String>any(), Mockito.<HttpMethod>any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), Mockito.<Map<String, Object>>any());
    }
}

