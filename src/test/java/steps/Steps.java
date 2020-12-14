package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.homework.framework.managers.ManagerPages;

public class Steps {

    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getStartPage() {
        app.getStartPage();
    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void selectBaseMenu(String nameBaseMenu) {
        app.getStartPage().selectBaseMenu(nameBaseMenu);
    }

    @Когда("^Выбор подменю '(.*)'$")
    public void selectSubMenu(String nameBaseMenu) {
        app.getStartPage().selectSubMenu(nameBaseMenu);
    }

    @Когда("^Заполняем форму поле/значение$")
    public void fillFields(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getMortgagePage().fillInputFields(raw.get(0), raw.get(1));
                }
        );
    }

    @Когда("^Выключение кнопки '(.*)'$")
    public void getOffButton(String nameOfButton) {
        app.getMortgagePage().buttonGetOff(nameOfButton);
    }

    @Когда("^Включение кнопки '(.*)'$")
    public void getOnButton(String nameOfButton) {
        app.getMortgagePage().buttonGetOn(nameOfButton);
    }

    @Тогда("^Проверка поля '(.*)'$")
    public void checkSum (String nameOfSumToBeChecked) {
        app.getMortgagePage().checkSum(nameOfSumToBeChecked);
    }

}
