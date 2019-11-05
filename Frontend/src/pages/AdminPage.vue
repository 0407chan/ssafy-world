<template>
    <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        item-key="uidx"
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
                <v-btn color="primary" dark class="mb-2" @click="editAllItem(selected)">선택 유저 수정</v-btn>
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
import Swal from 'sweetalert2'

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
            const index = this.desserts.indexOf(item)

            let params = {
                uidx: this.desserts[index].uidx,
                staff: this.desserts[index].staff.abbr,
            }
            confirm('정말로 해당 유저의 등급을 변경하시겠습니까?') &&
                    await this.adminUpdateUser(params) &&
                    this.successAlert('수정 완료!', '해당 유저의 등급이 바뀌었습니다.')
        },
        async editAllItem(items) {
            if (confirm('정말로 선택한 유저의 등급을 모두 변경하시겠습니까?')) {
                let getStaff = new Map();
                for(var j = 0; j < this.desserts.length; ++j)
                    getStaff.set(this.desserts[j].uidx, this.desserts[j].staff.abbr);
                
                for(var i = 0; i < items.length; ++i) {
                    let params = {
                        uidx: items[i].uidx,
                        staff: getStaff.get(items[i].uidx),
                    }
                    await this.adminUpdateUser(params)
                }
                this.selected = []

                this.successAlert('수정 완료!', '선택한 유저의 등급이 모두 바뀌었습니다.')
            }
        },

        ...mapActions("data", ['adminDeleteUser']),
        async deleteItem(item) {
            const index = this.desserts.indexOf(item)

            confirm('정말로 해당 유저를 삭제하시겠습니까?') &&
                    await this.adminDeleteUser(this.desserts[index].uidx)
        },

        successAlert(title, text) {
            Swal.fire({
                title: title,
                text: text,
                type: 'success',
                showConfirmButton: false,
                timer: 1000
            })
        },
    },
}
</script>

<style scoped>
    .elevation-1 {
        margin: 2%;
    }
</style>
