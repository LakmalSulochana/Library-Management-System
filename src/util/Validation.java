package util;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import javafx.scene.control.TextField;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validation {

    public static Object validate(LinkedHashMap<TextField, Pattern> map, JFXButton btn){
        for (TextField textFieldKey:map.keySet()){
            Pattern patternValue=map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()){
                if (! textFieldKey.getText().isEmpty()){
                    textFieldKey.setStyle("-fx-text-fill: red");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: Green");
        }
        btn.setDisable(false);
        return true;

    }
}

