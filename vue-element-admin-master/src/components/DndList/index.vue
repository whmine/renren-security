<template>
  <div class="dndList">
    <div :style="{width:widthLeft}" class="dndList-list">
      <h3>{{ listLeftTitle }}</h3>
      <draggable :set-data="setData" :list="listLeft" group="article" class="dragArea">
        <div v-for="element in listLeft" :key="element.id" class="list-complete-item">
          <div class="list-complete-item-handle">{{ element.cnName }}[{{ element.name }}]</div>
          <div style="position:absolute;right:0px;">
            <span
              style="float: right ;margin-top: -20px;margin-right:5px;"
              @click="deleteEle(element)"
            >
              <i style="color:#ff4949" class="el-icon-delete" />
            </span>
          </div>
        </div>
      </draggable>
    </div>
    <div :style="{width:widthRight}" class="dndList-list">
      <h3>{{ listRightTitle }}</h3>
      <draggable :list="listRight" group="article" class="dragArea">
        <div v-for="element in listRight" :key="element.id" class="list-complete-item">
          <div
            class="list-complete-item-handle2"
            @click="pushEle(element)"
          >{{ element.cnName }}[{{ element.name }}]</div>
        </div>
      </draggable>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";

export default {
  name: "DndList",
  components: { draggable },
  props: {
    listLeft: {
      type: Array,
      default() {
        return [];
      }
    },
    listRight: {
      type: Array,
      default() {
        return [];
      }
    },
    listLeftTitle: {
      type: String,
      default: "listLeft"
    },
    listRightTitle: {
      type: String,
      default: "listRight"
    },
    widthLeft: {
      type: String,
      default: "48%"
    },
    widthRight: {
      type: String,
      default: "48%"
    }
  },
  methods: {
    isNotInlistLeft(v) {
      return this.listLeft.every(k => v.id !== k.id);
    },
    isNotInlistRight(v) {
      return this.listRight.every(k => v.id !== k.id);
    },
    deleteEle(ele) {
      console.log("deleteEle" + ele);
      for (const item of this.listLeft) {
        if (item.id === ele.id) {
          const index = this.listLeft.indexOf(item);
          this.listLeft.splice(index, 1);
          this.$emit("deleteEle", ele.id);
          break;
        }
      }
      if (this.isNotInlistRight(ele)) {
        this.listRight.unshift(ele);
      }
    },
    pushEle(ele) {
      debugger;
      console.log("pushEle" + ele);
      for (const item of this.listRight) {
        if (item.id === ele.id) {
          const index = this.listRight.indexOf(item);
          this.listRight.splice(index, 1);
          break;
        }
      }
      if (this.isNotInlistLeft(ele)) {
        this.$emit("pushEle", ele.id);
        this.listLeft.push(ele);
      }
    },
    setData(dataTransfer) {
      // to avoid Firefox bug
      // Detail see : https://github.com/RubaXa/Sortable/issues/1012
      dataTransfer.setData("Text", "");
    }
  }
};
</script>

<style lang="scss" scoped>
.dndList {
  background: #fff;
  padding-bottom: 40px;
  &:after {
    content: "";
    display: table;
    clear: both;
  }
  .dndList-list {
    float: left;
    padding-bottom: 30px;
    &:first-of-type {
      margin-right: 2%;
    }
    .dragArea {
      margin-top: 15px;
      min-height: 50px;
      padding-bottom: 30px;
    }
  }
}

.list-complete-item {
  cursor: pointer;
  position: relative;
  font-size: 14px;
  padding: 5px 12px;
  margin-top: 4px;
  border: 1px solid #bfcbd9;
  transition: all 1s;
}

.list-complete-item-handle {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-right: 50px;
}

.list-complete-item-handle2 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-right: 20px;
}

.list-complete-item.sortable-chosen {
  background: #4ab7bd;
}

.list-complete-item.sortable-ghost {
  background: #30b08f;
}

.list-complete-enter,
.list-complete-leave-active {
  opacity: 0;
}
</style>
