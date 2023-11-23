<template>
    <div>
        <table style="width:100%">
            <colgroup>
                <col style="width:25%">
                <col style="width:25%">
                <col style="width:25%">
                <col style="width:25%">
            </colgroup>
            <tbody>
                <tr>
                    <td>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" content-cols-sm content-cols-lg="7" label="Search Type" label-for="schType">
                        <b-form-select v-model="schType" class="mb-3" size="sm" id="schType">
                            <b-form-select-option :value="null">Please select an option</b-form-select-option>
                            <b-form-select-option :key="i" :value="row.code"  v-for="(row,i) in scrTypelist">{{ row.codeNm }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                    </td>
                    <td>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" content-cols-sm content-cols-lg="7" label="Keyword" label-for="schVal">
                        <b-form-input v-model="schVal" placeholder="Please enter your search term." type="search" @keyup.enter="keyPress"></b-form-input>
                    </b-form-group>
                    </td>
                    <td>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" content-cols-sm content-cols-lg="7" label="Group" label-for="schUsrGrp">
                        <b-form-select v-model="schUsrGrp" class="mb-3" size="sm" id="schUsrGrp">
                            <b-form-select-option :value="null">Please select an option</b-form-select-option>
                            <b-form-select-option :key="i" :value="row.code"  v-for="(row,i) in userGroupList">{{ row.codeNm }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                    </td>
                    <td>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" content-cols-sm content-cols-lg="7" label="Use Y/N" label-for="schUseYn">
                        <b-form-select v-model="schUseYn" class="mb-3" size="sm" id="schUseYn">
                            <b-form-select-option :value="null">Please select an option</b-form-select-option>
                            <b-form-select-option :key="i" :value="row.code"  v-for="(row,i) in useYnlist">{{ row.codeNm }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="search-footer">
        <b-button variant="info" @Click="fnSearch">Search</b-button>
        <b-button variant="outline-dark" @Click="searchBoxToggle">Close</b-button>
    </div>
    <!-- resizable 적용 테스트(0) : 버튼 주석처리 후 style .dx-resizable-handle::after 로 대체 -->
    <button type="button" class="btn-h__splitter"><i class="ri-more-fill"></i></button>
    <div class="demo-container">
        <div id="grid">
            <b-table 
            striped hover 
            :items="items" 
            :fields="fields"
            :select-mode="single"
            responsive="sm"
            ref="selectableTable"
            selectable
            @row-selected="onRowSelected"
            >
                <!-- Example scoped slot for select state illustrative purposes -->
                <template #cell(selected)="{ rowSelected }">
                    <template v-if="rowSelected">
                    <span aria-hidden="true">&check;</span>
                    <span class="sr-only">Selected</span>
                    </template>
                    <template v-else>
                    <span aria-hidden="true">&nbsp;</span>
                    <span class="sr-only">Not selected</span>
                    </template>
                </template>            
            </b-table>
        </div>
    </div>
</template>

<script>
  import $ from 'jquery';
  const gridHeight = 0;
  export default {
    data() { //변수생성
        return {
            requestBody: {}, //리스트 페이지 데이터전송
            schType: '',
            schUsrGrp: '',
            schUseYn: '',
            schVal:'',
            scrTypelist: [],
            userGroupList: [],
            useYnlist: [],
            fields: [
                {
                    key: 'usrId',
                    label: 'ID',
                    sortable: true
                },
                {
                    key: 'usrNm',
                    label: 'Name',
                    sortable: true
                },
                {
                    key: 'usrTel',
                    label: 'Telephone',
                    sortable: true,
                },
                {
                    key: 'email',
                    label: 'E-Mail',
                    sortable: true
                },
                {
                    key: 'groupSeq',
                    label: 'Group',
                    sortable: true
                },
                {
                    key: 'roleSeq',
                    label: 'Role',
                    sortable: true
                },
                {
                    key: 'useYn',
                    label: 'Use Y/N',
                    sortable: true
                },
            ],
            items: {},
            selectMode: 'single',
        }
    },
    mounted() {
      this.fnInit()
    },
    methods: {
		fnInit() {
            this.$axios.get("/admin/userList", {
                params: this.requestBody,
                headers: {},
            }).then((res) => {      
                this.scrTypelist = res.data.scrTypelist  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
                this.userGroupList = res.data.userGroupList  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
                this.useYnlist = res.data.useYnlist  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.

            }).catch((err) => {
                if (err.message.indexOf('Network Error') > -1) {
                alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
                }
            })
		},
        fnSearch() {
            this.requestBody = { // 데이터 전송
                schType: this.schType,
                schVal: this.schVal,
                schUsrGrp: this.schUsrGrp,
                schUseYn: this.schUseYn,
				skip: 0,
				take: 50,
				orderby: "",	                
            }

            this.$axios.post("/admin/getUserList", null,{
                params: this.requestBody,
                headers: {},
            }).then((res) => {      
                this.items = res.data.list  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.

            }).catch((err) => {
                if (err.message.indexOf('Network Error') > -1) {
                alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
                }
            })
		},
        searchBoxToggle() {
            alert("toggle")
            if ($('.detail-searchBox').css('display') === 'block') {
    			$('.detail-searchBox').css('display', 'none');
            } else {
                $('.detail-searchBox').css('display', 'block');
            }

            //그리드 Height 값 조정 : 검색조건 영역 오픈 여부에 따라 height 값 조절
            //var searchHeight = $(".detail-searchBox").height();
            var searchOutHeight = $('.detail-searchBox').outerHeight(true); //margin포함한 height값
            var checkHeight = $('#FormSearch').height();	//검색조건 영역 height 체크

            if(checkHeight>0){ //검색조건 영역이 open된 경우
                $('#grid').css('height', gridHeight-searchOutHeight);
            }else{
                $('#grid').css('height', gridHeight);
            }            
        },
		keyPress() {
            this.fnSearch()
		},
        onRowSelected(items) {
            alert(items.usrNm)
            this.selected = items
        },
    },
  }  
</script>