<template>
    <div>
        <v-container fluid grid-list-md>
            <v-layout row wrap >
                <v-flex md9 xs9>
                    <v-layout row wrap>
                        <v-flex md12>
                            <v-card v-for="order in orders" :key="order.id">
                                <div>Заказ: ID{{ order.id }}</div>
                                <v-layout row wrap>
                                    <v-flex md4>
                                        <div>Столик: №{{ order.table }}</div>
                                        <div>Статус: {{ order.status }}</div>
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
            };
        },
        created: function() {
            axios
                .get("/api/order/")
                .then(response => (this.orders = response.data))
                .catch(error => console.log(error));
            setInterval(function() {
                location.reload();
            }, 5000);
        }
    };
</script>

<style scoped>

</style>