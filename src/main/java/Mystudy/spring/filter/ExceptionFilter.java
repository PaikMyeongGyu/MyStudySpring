package Mystudy.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class ExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try{
            chain.doFilter(request, response);
        } catch(IllegalArgumentException e){
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        } catch(RuntimeException e){
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        } finally {
            log.info("Dispatcher Type: [{}]", request.getDispatcherType());
        }
    }
}
