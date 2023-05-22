package it.unibz.infosec.examproject.user.domain.access;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private ActiveUserStore activeUserStore;

    public LoggedUser(String email, ActiveUserStore activeUserStore) {
        this.email = email;
        this.activeUserStore = activeUserStore;
    }

    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getEmail())) {
            users.add(user.getEmail());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getEmail())) {
            users.remove(user.getEmail());
        }
    }
}
