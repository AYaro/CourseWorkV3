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
                            <v-card v-for="ro in table" :key="ro.id">
                                <!--<div>Заказ: ID{{ ro.id }}</div>-->
                                <div>День {{ (new Date(ro.day)).getDate() }} месяц </div>
                                <v-layout row wrap>
                                    <v-flex md4>
                                        <div>Начало работы {{ ro.start_time }}</div>
                                        <div>Конец  работы {{ ro.end_time }}</div>
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
        name: "TimeTable",
        data() {
            return {
                table: [{"id":1,"day":"2019-04-11T02:08:24.170+0000","start_time":"01:02:04","end_time":"03:02:04"},{"id":2,"day":"2019-04-11T02:08:24.198+0000","start_time":"06:02:04","end_time":"09:02:04"}]
            };
        },
        created: function() {
            axios
                .get("/api/order/")
                .then(response => (this.table = response.data))
                .catch(error => console.log(error));
        }
    }
</script>

<style>
.select {
    border: 1px solid lightgray;
    width: auto;
}
</style>