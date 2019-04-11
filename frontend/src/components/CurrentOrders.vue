<template>
  <div>
    <v-container fluid grid-list-md>
      <v-layout row wrap v-if="!showDetails">
        <v-flex md3 xs3>
          <v-navigation-drawer mobile-break-point="600px">
            <v-toolbar flat>
              <v-list>
                <v-list-tile>
                  <v-list-tile-title class="title">
                    Меню
                  </v-list-tile-title>
                </v-list-tile>
              </v-list>
            </v-toolbar>

            <v-divider></v-divider>

            <v-list dense class="pt-0">
              <v-list-tile
                v-for="item in items"
                :key="item.title"
                v-on:click="currentCategory = item.title"
              >
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-navigation-drawer>
        </v-flex>

        <v-flex md9 xs9>
          <v-layout row wrap>
            <v-flex md12>
              <v-card v-for="order in orders" :key="order.id">
                <div>Заказ: ID{{ order.id }}</div>
                <v-layout row wrap>
                  <v-flex md4>
                    <div>Столик: №{{ order.table }}</div>
                    <div>Статус: {{ order.status }}</div>
                    <div>
                      Изменить статус:
                      <select v-model="order.status" class="select">
                        <option v-for="status in statuses" v-bind:key="status">
                          {{ status }}
                        </option>
                      </select>
                    </div>
                    <div>Комментарий: {{ order.comment }}</div>
                  </v-flex>
                  <v-flex md8>
                      <v-card
                        v-for="orderedDish in order.orderedDishes"
                        :key="orderedDish.id"
                      >
                        <v-layout row wrap>
                          <v-flex md3>
                            {{ orderedDish.dish.name   }}
                          </v-flex>
                          <v-flex md2> кол: {{ orderedDish.quantity }} </v-flex>
                          <v-flex md3>
                            статус: {{ orderedDish.status }}
                          </v-flex>
                          <v-flex md4>
                            изменить статус: <select v-model="order.status" class="select">
                              <option
                                v-for="status in statuses"
                                v-bind:key="status"
                              >
                                {{ status }}
                              </option>
                            </select>
                          </v-flex>
                        </v-layout>
                      </v-card>
                  </v-flex>
                </v-layout>
              </v-card>
            </v-flex>
          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

import Vue from "vue";
import Vuesax from "vuesax";

import "vuesax/dist/vuesax.css"; //Vuesax styles
Vue.use(Vuesax);

export default {
  name: "CurrentOrders",
  data() {
    return {
      items: [{ title: "Заказы" }, { title: "Расписание" }],
      orders: [{ id: 1, orderedDishes: { name: "Ad" } }],
      currentCategory: "Заказы",
      statuses: ["Foo", "Bar", "Fizz", "Buzz"]
    };
  },
  created: function() {
    axios
      .get("http://localhost:9090/api/order/")
      .then(response => (this.orders = response.data))
      .catch(error => console.log(error));
  }
};
</script>

<style>
.select {
  border: 1px solid lightgray;
  width: auto;
}
</style>
