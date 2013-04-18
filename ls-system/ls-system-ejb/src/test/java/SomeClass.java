import javax.annotation.PostConstruct;

/**
 * User: michau
 * Date: 4/18/13
 * Time: 9:19 PM
 */
public class SomeClass {

    private String someString;

    @PostConstruct
    public void initialize(){
        someString = "lalalla";
    }

}
