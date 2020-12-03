package contact.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import contact.model.Person;
import contact.util.DateUtil;

/**
 * ����ó ������ �����ϴ� ���̾�α�
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * ��Ʈ�ѷ� Ŭ������ �ʱ�ȭ�Ѵ�.
     * �� �޼���� fxml ������ �ε�� �� �ڵ����� ȣ��ȴ�.
     */
    @FXML
    private void initialize() {
    }

    /**
     * �� ���̾�α��� ���������� �����Ѵ�.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * ���̾�α׿��� ����� ����ó�� �����Ѵ�.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("yyyy�� mm�� dd��");
    }

    /**
     * ����ڰ� OK�� Ŭ���ϸ� true��, �� �ܿ��� false�� ��ȯ�Ѵ�.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * ����ڰ� OK�� Ŭ���ϸ� ȣ��ȴ�.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * ����ڰ� cancel�� Ŭ���ϸ� ȣ��ȴ�.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * �ؽ�Ʈ �ʵ�� ����� �Է��� �˻��Ѵ�.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "<��>�� �߸���!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "<�̸�>�� �߸���!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "<�Ÿ�>�� �߸���!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "<�����ȣ>�� �߸���!\n";
        } else {
            // ���� ��ȣ�� int Ÿ������ ��ȯ�Ѵ�.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "<�����ȣ>�� �߸��� (������ ��� �ʼ�)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "<����>�� �߸���!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "<�������>�� �߸���!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "<�������>�� �߸���.  yyyy.dd.mm ������ ����ϼ���!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // ���� �޽����� �����ش�.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("�ʵ尪�� �߸���!");
            alert.setHeaderText("�ʵ尪�� �����ϼ���!!!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
