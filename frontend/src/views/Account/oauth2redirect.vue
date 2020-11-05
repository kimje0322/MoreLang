<template>
    <div>

    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "oauth2redirect",
    created() {
        console.log("create oauth2redirect");
        console.log(this.$route.query)
        const code = this.$route.query.code;
        if (code) {
            const fd = new FormData();
            fd.append("code", code);
            axios
                .post(`${this.$store.state.ServerURL}/newuser/naverlogin`, fd)
                .then(response => {
                    console.log("성공!");
                    console.log(response);
            });
            this.$router.replace('/Login')
        }
        else {
            this.$router.replace("/")
        }
    }
}
</script>

<style scoped>

</style>