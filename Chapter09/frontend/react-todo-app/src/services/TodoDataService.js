import axios from 'axios'

const TODO_API_ROOT = 'http://localhost:8080'

class TodoDataService {
    retrieveAllTodos(name) {
        return axios.get(`${TODO_API_ROOT}/users/${name}/todos`);
    }

    createTodo(name, todo) {
        return axios.post(`${TODO_API_ROOT}/users/${name}/todos`, todo);
    }
}

export default new TodoDataService()