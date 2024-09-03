package org.example.client;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LocalPortLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(request.getURI().getHost(), request.getURI().getPort()));
        int localPort = socket.getLocalPort();
        socket.close();
        log.info("Local port used for request: " + localPort);
        return response;
    }
}

