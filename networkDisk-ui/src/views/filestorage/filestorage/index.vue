<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="镜像存储id" prop="ossId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.ossId"-->
      <!--          placeholder="请输入镜像存储id"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="文件名" prop="fileName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileName"-->
      <!--          placeholder="请输入文件名"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="名称" prop="originalName">
        <el-input
          v-model="queryParams.originalName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="文件后缀名" prop="fileSuffix">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileSuffix"-->
      <!--          placeholder="请输入文件后缀名"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="URL地址" prop="url">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.url"-->
      <!--          placeholder="请输入URL地址"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['filestorage:filestorage:add']"
        >文件上传
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['filestorage:filestorage:edit']"-->
      <!--        >重命名</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-down"
          size="mini"
          :disabled="single"
          @click="handleDownload"
          v-hasPermi="['filestorage:filestorage:edit']"
        >批量下载
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['filestorage:filestorage:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['filestorage:filestorage:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filestorageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="文件存储主键" align="center" prop="filestorageId" v-if="true"/>-->
      <!--      <el-table-column label="用户id" align="center" prop="userId" />-->
      <!--      <el-table-column label="镜像存储id" align="center" prop="ossId" />-->
      <!--      <el-table-column label="文件名" align="center" prop="fileName" />-->
      <el-table-column label="文件名称" align="center" prop="originalName"/>
      <el-table-column label="文件大小(kb)" align="center" prop="fileSize"/>
      <el-table-column label="文件类型" align="center" prop="fileSuffix"/>
      <el-table-column label="变动时间" align="center" prop="updateTime"/>
      <!--      <el-table-column label="URL地址" align="center" prop="url" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['filestorage:filestorage:edit']"
          >重命名
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="downloadOne(scope.row)"
            v-hasPermi="['filestorage:filestorage:edit']"
          >下载
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['filestorage:filestorage:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 上传文件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="用户id" prop="userId">-->
        <!--          <el-input v-model="form.userId" placeholder="请输入用户id" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="镜像存储id" prop="ossId">-->
        <!--          <el-input v-model="form.ossId" placeholder="请输入镜像存储id" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="文件名" prop="fileName">-->
        <!--          <el-input v-model="form.fileName" placeholder="请输入文件名" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="原名" prop="originalName">-->
        <!--          <el-input v-model="form.originalName" placeholder="请输入原名" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="文件后缀名" prop="fileSuffix">-->
        <!--          <el-input v-model="form.fileSuffix" placeholder="请输入文件后缀名" />-->
        <!--        </el-form-item>-->
<!--        <el-form-item label="文件名">-->
<!--          <UserUpdataFile v-model="form.file"/>-->
<!--        </el-form-item>-->
        <el-upload
          class="pop-upload"
          ref="upload"
          action=""
          :file-list= "fileList"
          :auto-upload="false"
          :multiple="true"
          :on-change="handleChange"
          :on-remove="handleRemove">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitForm">上传到服务器</el-button>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
<!--        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>-->
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 修改用户文件存储对话框 -->
    <el-dialog :title="title" :visible.sync="openUpdata" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="原名" prop="originalName">
          <el-input v-model="form.originalName" placeholder="请输入原名"/>
        </el-form-item>
        <el-form-item label="文件名" prop="fileName">
          <el-input v-model="form.originalNameNew" placeholder="请输入文件名"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listFilestorage,
  getFilestorage,
  delFilestorage,
  addFilestorage,
  updateFilestorage, fileDownload
} from "@/api/filestorage/filestorage";
import UserUpdataFile from "@/components/FileUpload/userUpdataFile.vue";

export default {
  name: "Filestorage",
  components: {UserUpdataFile},
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户文件存储表格数据
      filestorageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层（上传文件）
      open: false,
      // 是否显示弹出层（修改文件名称）
      openUpdata: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        ossId: undefined,
        fileName: undefined,
        originalName: undefined,
        fileSuffix: undefined,
        url: undefined,
      },
      // 表单参数
      form: {
        files: [],
      },
      // 文件集合
      fileList: [],
      // 表单校验
      rules: {
        filestorageId: [
          {required: true, message: "文件存储主键不能为空", trigger: "blur"}
        ],
        userId: [
          {required: true, message: "用户id不能为空", trigger: "blur"}
        ],
        ossId: [
          {required: true, message: "镜像存储id不能为空", trigger: "blur"}
        ],
        fileName: [
          {required: true, message: "文件名不能为空", trigger: "blur"}
        ],
        originalName: [
          {required: true, message: "原名不能为空", trigger: "blur"}
        ],
        fileSuffix: [
          {required: true, message: "文件后缀名不能为空", trigger: "blur"}
        ],
        url: [
          {required: true, message: "URL地址不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户文件存储列表 */
    getList() {
      this.loading = true;
      listFilestorage(this.queryParams).then(response => {
        this.filestorageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openUpdata = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        filestorageId: undefined,
        userId: undefined,
        ossId: undefined,
        fileName: undefined,
        originalName: undefined,
        originalNameNew: undefined,//重命名
        fileSuffix: undefined,
        url: undefined,
        files: [],//文件
        createTime: undefined,
        createBy: undefined,
        updateTime: undefined,
        updateBy: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.filestorageId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 上传文件新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户文件存储";
    },
    /** 重命名按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const filestorageId = row.filestorageId
      getFilestorage(filestorageId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.openUpdata = true;
        this.title = "修改文件名称";
      });
    },
    /** 批量下载按钮操作 */
    handleDownload(row) {
      this.loading = true;
      this.reset();
      const filestorageId = row.filestorageId || this.ids
      getFilestorage(filestorageId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "文件下载";
      });
    },
    //文件上传（文件改变的钩子）
    handleChange(file, fileList) {
      this.fileList = fileList
    },
    //文件上传（文件删除的钩子）
    handleRemove(file, fileList) {
      this.fileList = fileList
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.filestorageId != null) {//修改提交
            updateFilestorage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.openUpdata = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {// 新增提交
            //处理数据
            if (this.fileList.length === 0) {//检测表单是否存在文件
              return this.$message.warning('请选取文件后再上传')
            }
            // 下面的代码将创建一个空的FormData对象:
            const formData = new FormData()
            // 你可以使用FormData.append来添加键/值对到表单里面；
            this.fileList.forEach((file) => {
              formData.append('files', file.raw)
              console.log(formData)
            })
            addFilestorage(formData).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
        this.form.files = []
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const filestorageIds = row.filestorageId || this.ids;
      this.$modal.confirm('是否确认删除用户文件存储编号为"' + filestorageIds + '"的数据项？').then(() => {
        this.loading = true;
        return delFilestorage(filestorageIds);
      }).then(res => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess(res.data.msg);
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('filestorage/export', {
        ...this.queryParams
      }, `filestorage_${new Date().getTime()}.xlsx`)
    },
    /** 单个下载按钮 */
    downloadOne(row) {
      this.$download.userFileDownload(row.filestorageId)
      // fileDownload(row)
    }
  }
};
</script>
