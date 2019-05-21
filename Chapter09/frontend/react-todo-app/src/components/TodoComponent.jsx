import React, { Component } from 'react';
import TodoDataService from '../services/TodoDataService';

const HARDCODED_USER_NAME = 'Jack'

class TodoComponent extends Component {

    state = {
        todos: [],
        error: false
    }

    constructor(props) {
        super(props);
        this.addTodo = this.addTodo.bind(this);
    }

    componentDidMount() {
        this.refreshTodos();
    }

    refreshTodos() {
        TodoDataService.retrieveAllTodos(HARDCODED_USER_NAME)
            .then(
                response => {
                    this.setState((prevState) => {
                        return {
                            todos: response.data
                        };
                    });

                }
            )
    }

    addTodo(e) {

        if (this._todoTextElement.value === "") {

            this.setState((prevState) => {
                return {
                    error: true
                };
            });

            this.preventFormFromReload(e);

            return
        }

        var newTodo = {
            desc: this._todoTextElement.value,
            id: -1,
            targetDate: new Date(),
            user: HARDCODED_USER_NAME
        };

        TodoDataService.createTodo(HARDCODED_USER_NAME, newTodo)
            .then(
                response => {
                    console.log(response);
                }
            )

        this.setState((prevState) => {
            return {
                todos: prevState.todos.concat(newTodo)
            };
        });

        this.resetTodoElement();

        this.preventFormFromReload(e);
    }

    resetTodoElement() {
        this._todoTextElement.value = "";
    }

    preventFormFromReload(e) {
        e.preventDefault();
    }


    render() {
        return (
            <div>
                <form onSubmit={this.addTodo}>
                    <input ref={(todoRef) => this._todoTextElement = todoRef} placeholder="What do you want to learn today?">
                    </input>
                    <button type="submit">add</button>
                    {this.state.error && <div class="alert alert-warning">Please enter a description</div>}
                    <ul>
                        {
                            this.state.todos.map(
                                todo => <li key={todo.id}>{todo.desc}</li>
                            )
                        }
                    </ul>
                </form>
            </div>

        );
    }
}
export default TodoComponent;