<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.firstName" placeholder="first name" />
    <input type="text" v-model="user.lastName" placeholder="last name" />
    <select v-model="user.position">
      <option value="Kitchen">Поваренок</option>
      <option value="Hall">Крыса Лариса</option>
    </select>
    <input type="text" v-model="user.passport" placeholder="passport" />
    <input type="text" v-model="user.email" placeholder="email" />
    <input
      type="text"
      v-model="user.salary_per_hour"
      placeholder="salary per hour"
    />

    <button @click="createUser()">Create User</button>

    <div v-if="showResponse">
      <h6>User created with Id: {{ response }}</h6>
    </div>

    <button v-if="showResponse" @click="retrieveUser()">
      Retrieve user {{ user.id }} data from database
    </button>

    <h4 v-if="showRetrievedUser">
      Retrieved User {{ retrievedUser.firstName }} {{ retrievedUser.lastName }}
    </h4>
  </div>
</template>

<script>
    /* eslint-disable no-console */

    import axios from "axios";
// import {AXIOS} from './http-common'
export default {
  name: "user",
  data() {
    return {
      response: [],
      errors: [],
      user: {
        lastName: "",
        firstName: "",
        password: "",
        position: "",
        salary_per_hour: 0,
        passport: 0,
        email: "",
        id: 0
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  methods: {
    // Fetches posts when the component is created.
    createUser() {
      var params = new URLSearchParams();
      params.append("firstName", this.user.firstName);
      params.append("lastName", this.user.lastName);
      params.append("password", this.user.password);
      params.append("position", this.user.position);
      params.append("salary_per_hour", this.user.salary_per_hour);
      params.append("passport", this.user.passport);
      params.append("email", this.user.email);

      axios
        .post(`/api/user`, params)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          this.user.id = response.data;
          // console.log(response.data);
          this.showResponse = true;
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    retrieveUser() {
      axios
        .get(`/api/user/` + this.user.id)
        .then(response => {
          // JSON responses are automatically parsed.
          this.retrievedUser = response.data;
          console.log(response.data);
          this.showRetrievedUser = true;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
