<template>
    <v-layout class="inner-wrap" fluid fill-height inner-wrap>
        <MyTable />
    </v-layout>
</template>

<script>
import MyTable from '@/components/mypage/MyTable.vue'

export default {
  name: 'MyPage',
  data() {
    return {

    };
  },
  components: {
    MyTable,
  },
}
</script>