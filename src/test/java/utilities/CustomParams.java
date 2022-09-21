package utilities;

import enums.FindWorkMenu;
import io.cucumber.java.ParameterType;

public class CustomParams {
    @ParameterType("WHY_BROOKSOURCE|OUR_PROCESS|SEEK_A_POSITION")
    public FindWorkMenu menu(String menu){
        return FindWorkMenu.valueOf(menu);
    }
}
