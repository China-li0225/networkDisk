<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'RuoYiDoc',
  data() {
    return {
      url: 'https://gitee.com/dromara/networkDisk/wikis/pages'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
