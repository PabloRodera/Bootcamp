// Pon a prueba tus conocimientos sobre JavaScript aqui
document.addEventListener('DOMContentLoaded', () => {
    
    const addTaskButton = document.querySelector('#addTaskButton');
    
    let tasks = [];
    
    function addTask() {
        const taskInput = document.querySelector('#taskInput');
        const taskName = taskInput.value.trim();

        if (taskName !== '') {
            const newTask = {
                id: Date.now(), // Genera un ID único usando la marca de tiempo actual
                name: taskName,
                completed: false
            };
            tasks.push(newTask);
            renderTasks(); //Ejemplo de Hoisting, se llama a la funcion renderTasks() antes de que se declarada.
            taskInput.value = '';
        }
    }
    
    function renderTasks() {
        const taskList = document.querySelector('#taskList');
        taskList.innerHTML = '';

        const completedTasks = tasks.filter(task => task.completed);
        const pendingTasks = tasks.filter(task => !task.completed);

        completedTasks.forEach(task => {
            const listItem = createTaskListItem(task);
            taskList.appendChild(listItem);
        })

        pendingTasks.forEach(task => {
            const listItem = createTaskListItem(task);
            taskList.appendChild(listItem);
        })

    }

    function createTaskListItem(task){
        const listItem = document.createElement('li');
        const taskName = task.name;
        listItem.innerHTML = `
                <article>
                    <input type="checkbox" class="task-checkbox" ${task.completed ? 'checked' : ''}>
                    <span class="task-text">${taskName}</span>
                </article>
                <i class="fa fa-trash"></i>`

        const checkbox = listItem.querySelector('.task-checkbox');
        checkbox.addEventListener('change', function () {
            task.completed = this.checked;
            renderTasks();
        });
            
        const deleteIcon = listItem.querySelector('.fa-trash');
        deleteIcon.addEventListener('click', function() {
            tasks = tasks.filter(t => t.id !== task.id);
            renderTasks();
        });
                      
        return listItem;
    }
   
    addTaskButton.addEventListener('click', addTask);
});

/*
    Scopes: En esta sección, la variable taskName se declara tanto en la función addTask como en la función createTaskListItem.
    En addTask, taskName se refiere al nombre de la tarea ingresada por el usuario.
    En createTaskListItem, taskName se refiere al nombre de la tarea.
    Ambas variables taskName son locales a sus respectivas funciones, y no se afectan entre sí debido a que están en scopes diferentes.
    La variable listItem también se usa en varios contextos locales, sin hacer colision entre si, ya que estan en distintos scopes.
*/

/*
    No le veía mucho el sentido al botón de "tarea completada"(eso, o no me he enterado si había que hacer algo extra con el), ya que no hace nada, simplemente es un check.
    He añadido una funcionalidad extra que no se valoraba en el ejercicio.
    Cuando marcas una tarea como completada, se muestra arriba de la lista, todas las tareas que tengan el check
    de completada, aparecerán las primeras en la lista.
*/
