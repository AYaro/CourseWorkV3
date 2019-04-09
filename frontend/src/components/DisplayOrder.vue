<template>
    <div>
        <div v-bind:key="order.id" v-for="order in orders">
            <h3>
                {{order.Status}}
            </h3>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DisplayOrder",
        data() {
            return {
                orders: [
                    {
                        id: 3,
                        Comment: "asd",
                        Status: "qwe",
                        start_time: 1,
                        end_time: null
                    },
                    {
                        id: 1,
                        Comment: "asd",
                        Status: "qwe",
                        start_time: 1,
                        end_time: null
                    }
                ]
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>