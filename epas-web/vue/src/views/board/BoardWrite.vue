<template>
    <div class="board-detail">
      <div class="common-buttons">
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
        <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
      </div>
      <div class="board-contents">
        <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
        <input type="text" v-model="author" class="w3-input w3-border" placeholder="작성자를 입력해주세요." v-if="idx === undefined">
      </div>
      <div class="board-contents">
        <textarea id="" cols="30" rows="10" v-model="contents" class="w3-input w3-border" style="resize: none;">
        </textarea>
      </div>
      <div class="common-buttons">
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
        <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
      </div>
    </div>
</template>
  
<script>
  export default {
    data() { //변수생성
      return {
        requestBody: this.$route.query,
        idx: this.$route.query.idx,
  
        title: '',
        author: '',
        contents: '',
        createdAt: ''
      }
    },
    mounted() {
      this.fnGetView()
    },
    methods: {
      fnGetView() {
        if (this.idx !== undefined) {
            this.$axios.get("/board/detail", {
                params: this.requestBody
            }).then((res) => {
                const resData = res.data;
                this.title = resData.data.title
                this.author = resData.data.author
                this.contents = resData.data.contents
                this.createdAt = resData.data.createdAt
            }).catch((err) => {
                console.log(err)
            })
        }
      },
      fnList() {
        delete this.requestBody.idx
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },
      fnView(idx) {
        this.requestBody.idx = idx
        this.$router.push({
          path: './detail',
          query: this.requestBody
        })
      },
      fnSave() {
        let apiUrl = '/board/creatBoard'
        this.form = {
          "idx": this.idx,
          "title": this.title,
          "contents": this.contents,
          "author": this.author
        }
  
        if (this.idx === undefined) {
          //INSERT
          this.$axios.post(apiUrl, this.form)
          .then((res) => {
            alert('글이 등록 되었습니다.')
            const resData = res.data;

            this.fnView(resData.data.idx)
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        } else {
          //UPDATE
          let updateUrl = '/board/updateBoard'
          this.$axios.post(updateUrl, this.form)
          .then((res) => {
            alert('글이 수정 되었습니다.')
            const resData = res.data;

            this.fnView(resData.data.idx)
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        }
      }
    }
  }
</script>
<style scoped>
  
</style>