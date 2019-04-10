<template>
    <div>
        <div v-bind:key="order.id" v-for="order in orders">
            <h3>
                {{order.comment}}
            </h3>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: "DisplayOrder",
        data() {
            return {
                orders: []
            }
        },
        methods: {

        },
        created: function () {
            axios
                .get('/api/order')
                .then(response => {
                    this.orders = response.data;
                    console.log(this.orders);
                })
                .catch(error => console.log(error));
        }
    }
</script>

<style scoped>

</style>