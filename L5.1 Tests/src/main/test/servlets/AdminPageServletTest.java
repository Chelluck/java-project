package servlets;

import accountServer.AccountServer;
import accountServer.AccountServerI;


import org.junit.Test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminPageServletTest {
    private AccountServerI accountServer = mock(AccountServer.class);


    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);


        return response;
    }

    private HttpServletRequest getMockedRequest(String url) {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getPathInfo()).thenReturn(url);


        return request;
    }

    @Test
    public void doGet() throws Exception{
//        Field field = AccountServer.class.getDeclaredField("usersLimit");
//        field.setAccessible(true);
//        field.set(accountServer, 10);

        when(accountServer.getUsersLimit()).thenReturn(10);
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);

        HttpServletRequest request = getMockedRequest(AdminPageServlet.PAGE_URL);
        AdminPageServlet adminPage = new AdminPageServlet(accountServer);
        adminPage.doGet(request, response);
        assertEquals("10", stringWriter.toString().trim());
    }
}