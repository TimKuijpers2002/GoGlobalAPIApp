package GoGlobalProject.APIApp.Model;

import GoGlobalProject.APIApp.Enums.FormType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Form implements Serializable {

    @Column(name = "form_type")
    private String formType;
    @Column(name = "name_submitter")
    private String nameSubmitter;
    @Column(name = "email_submitter")
    private String emailSubmitter;
    @Column(name = "form_content")
    private String formContent;

}
