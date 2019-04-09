<template>
    <div>
        <div v-for="order in orders">
            <h3>
                {{order}}
            </h3>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DisplayOrder",
        data() {
            return {
                orders: []
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>