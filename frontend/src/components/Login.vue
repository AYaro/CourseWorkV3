<template>
  <div class="form">
    <div class="login">
      <form id="signin" @submit.prevent="processSignIn">
        <h1>Авторизация</h1>
        <h3>Введите логин:</h3>
        <input type="text" v-model="username" placeholder="логин" />
        <h3>Введите пароль:</h3>
        <input type="password" v-model="password" placeholder="пароль" />
        <button
          type="submit"
          style="display: flex; margin: 10px auto 10px auto"
        >
          Войти
        </button>
      </form>
      <div id="showError" class="error" v-bind:class="{ showError: !error }">
        Some server error
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data: function() {
    return {
      username: "",
      password: "",
      error: false
    };
  },
  methods: {
    processSignIn: function() {
      axios
        .post(
          "http://localhost:9090/login",
          "username=" + this.username + "&password=" + this.password
        )
        .then(function() {
          window.location = "/index";
        })
        .catch(e => {
          this.error = true;
          this.errors.push(e);
        });
    },
    processRegistration: function() {
      "#error".slideUp();
    },
    initRegistration: function() {
      "#registration".slideToggle();
    }
  },
  watch: {
    username: function() {
      this.error = false;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.form {
  padding: 50px;
}
.login {
  margin: auto;
  min-width: 300px;
  width: 20%;
  text-align: center;
  border: 1px solid black;
  border-radius: 16px;
}
.error {
  color: darkred;
}
.showError {
  display: none;
}
</style>
