<template>
    <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="name"
        show-select
        class="elevation-1">

        <template v-slot:top>
            <v-toolbar flat color="white">
                <v-toolbar-title>User List</v-toolbar-title>
                <v-divider
                    class="mx-4"
                    inset
                    vertical>
                </v-divider>
                <v-spacer></v-spacer>
                <v-dialog max-width="500px">
                    <template v-slot:activator="{ on }">
                        <v-btn color="primary" dark class="mb-2" v-on="on">전체 수정하기</v-btn>
                    </template>
                </v-dialog>
            </v-toolbar>
        </template>

        <template v-slot:item.staffAction="{ item }">
            <v-layout row wrap>
                <v-flex xs2>
                    <v-select
                        :items="items"
                        v-model="item.staff"
                        label="Select"
                        single-line
                        item-text="staff"
                        item-value="abbr"
                        return-object
                        persistent-hint>
                    </v-select>
                </v-flex>
            </v-layout>
        </template>

        <template v-slot:item.action="{ item }" align="center" justify="space-around">
            <v-icon small class="mr-2" @click="editItem(item)">
                mdi-pencil
            </v-icon>
            <v-icon small @click="deleteItem(item)">
                mdi-delete
            </v-icon>
        </template>

    </v-data-table>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'AdminPage',
    data () {
        return {
            selected: [],
            headers: [
                {
                    text: '번호',
                    align: 'left',
                    sortable: false,
                    value: 'uidx',
                },
                { text: '아이디',   value: 'uid'                            },
                { text: '이름',     value: 'uname'                          },
                { text: '등급',     value: 'staffAction',                   },
                { text: 'Actions',  value: 'action',        sortable: false },
            ],
            desserts: [],
            select: [],
            items: [
                { staff: '관리자',  abbr: 0     },
                { staff: '프로',    abbr: 1     },
                { staff: '교육생',  abbr: 2     },
            ],
            changed: false,
        }
    },
    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
        },
    },
    created() {
        this.getUsersAction()
    },
    methods: {
        ...mapActions("data", ['getUsers']),
        async getUsersAction() {
            let res = await this.getUsers()

            for (var i = 0; i < res.data.length; ++i) {
                let staff = res.data[i].staff
                let grade = ''
                     if (staff == 0)    grade = '관리자'
                else if (staff == 1)    grade = '프로'
                else if (staff == 2)    grade = '교육생' 

                this.select.push({
                    staff: grade,
                    abbr: staff,
                })

                this.desserts.push({
                    uidx: res.data[i].uidx,
                    uid: res.data[i].uid,
                    uname: res.data[i].uname,
                    staff: this.select[i].abbr,
                })
            }
        },

        ...mapActions("data", ['adminUpdateUser']),
        async editItem(item) {
            console.log(item)
            const index = this.desserts.indexOf(item)

            let params = {
                uidx: this.desserts[index].uidx,
                staff: this.desserts[index].staff.abbr,
            }
            console.log(params)
            confirm('정말로 해당 유저의 등급을 변경하시겠습니까?') &&
                    await this.adminUpdateUser(params)
        },

        ...mapActions("data", ['adminDeleteUser']),
        async deleteItem(item) {
            const index = this.desserts.indexOf(item)

            confirm('정말로 해당 유저를 삭제하시겠습니까?') &&
                    await this.adminDeleteUser(this.desserts[index].uidx)
        },
    },
}
</script>

<style scoped>
    .elevation-1 {
        margin: 2%;
    }
</style>
