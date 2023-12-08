function getSelectedIds() {

    console.log("Selected ids: ");
    var selectedIds = {};
    var radioGroups = {};

    var radioButtons = document.querySelectorAll('input[type="radio"]');
    radioButtons.forEach(function(radioButton) {
        var groupName = radioButton.name;
        if (groupName) {
            if (!radioGroups[groupName]) {
                radioGroups[groupName] = [];
            }
            radioGroups[groupName].push(radioButton);
        }
    });

    for (var groupName in radioGroups) {
        radioGroups[groupName].forEach(function(radioButton) {
            if (radioButton.checked) {
                if (!selectedIds[groupName]) {
                    selectedIds[groupName] = [];
                }
                selectedIds[groupName].push(radioButton.id);
            }
        });
    }

}

// Находим все группы radioButton
var radioGroups = document.querySelectorAll('input[type="radio"]');
var groupNames = {};

// Проходим по каждой группе и изменяем имя на 'group'+порядковый номер группы, сохраняя одинаковые имена
radioGroups.forEach(function(radioGroup) {
    if (!groupNames.hasOwnProperty(radioGroup.name)) {
        groupNames[radioGroup.name] = 'group' + (Object.keys(groupNames).length+1);
    }
    radioGroup.name = groupNames[radioGroup.name];
});

var table = document.getElementById("answersTable");
var rows = table.getElementsByTagName("tr");

for (var i = 0; i < rows.length; i++) {
    var cells = rows[i].getElementsByTagName("td");
    for (var j = 0; j < cells.length; j++) {
        if (j == 1) { // Второй столбец
            var input = cells[j].getElementsByTagName("input")[0];
            input.setAttribute("name", "answer" + (i));
        }
    }
}

// Находим все radioButton
var radioButtons = document.querySelectorAll('input[type="radio"]');

// Проходим по каждой кнопке и добавляем обработчик события change
radioButtons.forEach(function(radioButton) {
    radioButton.addEventListener('change', function() {
        var questionNumber = radioButton.name.slice(-1); // Имя группы radioButton
        var answerId = radioButton.id; // id выбранной кнопки
        var table = document.getElementById('answersTable');
        table.rows[questionNumber].cells[1].getElementsByTagName("input")[0].value = answerId;
    });
});
