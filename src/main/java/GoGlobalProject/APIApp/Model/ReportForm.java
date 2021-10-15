package GoGlobalProject.APIApp.Model;

import GoGlobalProject.APIApp.Enums.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportForm{

    private Form baseForm;
    private String name;
    private ReportCategory reportCategory;

}
