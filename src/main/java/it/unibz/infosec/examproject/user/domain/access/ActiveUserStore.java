package it.unibz.infosec.examproject.user.domain.access;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActiveUserStore {

    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<String>();
    }


}
