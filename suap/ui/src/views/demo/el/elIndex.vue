<template>
  <div v-if="false">
    basic 基础组件
    basic/button 按钮
    basic/border 边框
    basic/color 色彩
    basic/container 布局容器
    basic/icon 图标
    basic/layout 布局
    basic/link 链接
    basic/text 文本
    basic/scrollbar 滚动条
    basic/space 间距
    basic/typography 排版

    form 表单组件
    autocomplete 自动补全输入框
    cascader 级联选择器
    checkbox 多选框
    color_picker 取色器
    date_picker 日期选择器
    datetime_picker 日期时间选择器
    form 表单
    input 输入框
    input_number 数字输入框
    radio 单选框
    rate 评分
    select 选择器
    virtualized_select 虚拟化选择器
    slider 滑块
    switch 开关
    time_picker 时间选择器
    time_select 时间选择
    transfer 穿梭框
    upload 上传

    data 数据展示
    avatar 头像
    badge 徽章
    calendar 日历
    card 卡片
    carousel 走马灯
    collapse 折叠面板
    descriptions 描述列表
    empty 空状态
    image 图片
    infinite_scroll 无限滚动
    pagination 分页
    progress 进度条
    result 结果
    skeleton 骨架屏
    table 表格
    virtualized_table 虚拟化表格
    tag 标签
    timeline 时间线
    tour 漫游式引导
    tree 树形控件
    treeselect 树形选择
    virtualized_tree 虚拟化树形控件
    statistic 统计组件

    navigation 导航
    affix 固钉
    backtop 回到顶部
    breadcrumb 面包屑
    dropdown 下拉菜单
    menu 菜单
    page_header 页头
    steps 步骤条
    tabs 标签页

    feedback 反馈组件
    alert 提示
    dialog 对话框
    drawer 抽屉
    loading 加载
    message 消息提示
    message_box 消息弹出框
    notification 通知
    popconfirm 气泡确认框
    popover 弹出框
    tooltip 文字提示

    others 其他
    divider 分割线
    watermark 水印
  </div>
  <div>
    <el-table data="oaContractDataList">
      <el-table-column label="Info">
        <el-table-column align="center" label="序号" prop="no"/>
        <el-table-column align="center" label="合同编号" prop="contractNo" width="200"/>
        <el-table-column align="center" label="是否关闭" prop="closed"/>
        <el-table-column align="center" label="客户名称" prop="customerName" width="200"/>
        <el-table-column align="center" label="项目名称" prop="projectName" width="300"/>
        <el-table-column align="center" label="业务归属" prop="business"/>
        <el-table-column align="center" label="一级类型" prop="firstType"/>
        <el-table-column align="center" label="二级类型" prop="secondType"/>
        <el-table-column align="center" label="三级类型" prop="thirdType"/>
      </el-table-column>
      <el-table-column align="center" label="合同实施类型" prop="contractType"/>
      <el-table-column align="center" label="经营地区负责人" prop="areaLeader"/>
      <el-table-column align="center" label="成本中心代码" prop="centerCode"/>
      <el-table-column align="center" label="地区" prop="area"/>
      <el-table-column align="center" label="合同金额" prop="contractAmountWithTax"/>
      <el-table-column align="center" label="合同归档年份" prop="archiveYear"/>
      <el-table-column align="center" label="合同归档时间" prop="archiveTime"/>
      <el-table-column align="center" label="合同目前负责人" prop="contractLeader"/>
      <el-table-column align="center" label="军团" prop="dept"/>
      <el-table-column align="center" label="成本中心负责人" prop="centerLeader"/>
      <el-table-column align="center" label="成本中心" prop="center"/>
      <el-table-column align="center" label="合同税率" prop="contractTaxRate"/>
      <el-table-column align="center" label="公司主导/自主立项" prop="corpLeading"/>
      <el-table-column align="center" label="签订人" prop="signatories"/>
      <el-table-column align="center" label="合同签订日期" prop="signDate" width="180"/>
      <el-table-column align="center" label="业务代码" prop="businessCode"/>
      <el-table-column align="center" label="业绩分类" prop="achievementType"/>
      <el-table-column align="center" label="招投标业绩分类" prop="biddingAchievementType"/>
      <el-table-column align="center" label="中标方式" prop="winningBidderType"/>
      <el-table-column align="center" label="业主单位" prop="ownerCorp"/>
      <el-table-column align="center" label="省公司中标金额" prop="bidderAmount"/>
      <el-table-column align="center" label="预付款比例" prop="prepaymentsRate"/>
      <el-table-column align="center" label="进度款比例" prop="progPaymentRate"/>
      <el-table-column align="center" label="验收款比例" prop="acceptPaymentRate"/>
      <el-table-column align="center" label="质保金比例" prop="warrantyRate"/>
      <el-table-column align="center" label="质保期" prop="warrantyPeriod"/>
      <el-table-column align="center" label="预付款金额" prop="prepaymentsAmount"/>
      <el-table-column align="center" label="质保金金额" prop="warrantyAmount"/>
      <el-table-column align="center" label="审计后合同金额" prop="auditContractAmountWithTax"/>
      <el-table-column align="center" label="项目验收日期" prop="acceptDate"/>
      <el-table-column align="center" label="审减的差额" prop="reductionDiff"/>
      <el-table-column align="center" label="审减比例" prop="reductionRate"/>
      <el-table-column align="center" label="结算资料归档日期" prop="infoArchiveYear"/>
      <el-table-column align="center" label="审计报告是否归档" prop="auditReportArchive"/>
      <el-table-column align="center" label="客户满意度调查表是否归档" prop="satisfactionSurveyArchive"/>
      <el-table-column align="center" label="已开票金额" prop="invoiceAmount"/>
      <el-table-column align="center" label="未开票金额" prop="notInvoiceAmount"/>
      <el-table-column align="center" label="开票比例" prop="invoiceRate"/>
      <el-table-column align="center" label="已开收据金额" prop="invoiceReceiptAmount"/>
      <el-table-column align="center" label="未开收据金额" prop="notInvoiceReceiptAmount"/>
      <el-table-column align="center" label="已回款金额" prop="returnAmount"/>
      <el-table-column align="center" label="回款比例" prop="returnRate"/>
      <el-table-column align="center" label="最后一次回款日期" prop="lastReturnDate"/>
      <el-table-column align="center" label="理论质保金到期日" prop="warrantyExpiryDate"/>
      <el-table-column align="center" label="合同未回款金额" prop="notReturnAmount"/>
      <el-table-column align="center" label="开票未回款金额" prop="invoiceNotReturnAmount"/>
      <el-table-column align="center" label="最后一次开票日期" prop="lastInvoiceDate"/>
      <el-table-column align="center" label="账龄(年）" prop="debtYear"/>
      <el-table-column align="center" label="外委合同额" prop="outsourceContractAmount"/>
      <el-table-column align="center" label="外委开票额" prop="outsourceInvoiceAmount"/>
      <el-table-column align="center" label="外委付款金额" prop="outsourceInvoicePayment"/>
      <el-table-column align="center" label="外委开票未付款" prop="outsourceInvoiceUnpaid"/>
      <el-table-column align="center" label="收入" prop="income"/>
      <el-table-column align="center" label="外委成本" prop="outsourceCost"/>
      <el-table-column align="center" label="施工费" prop="cnstrCost"/>
      <el-table-column align="center" label="项目毛利润" prop="projectGrossProfit"/>
      <el-table-column align="center" label="项目毛利率" prop="projectGrossMargin"/>
      <el-table-column align="center" label="中标服务费" prop="winningServiceCost"/>
      <el-table-column align="center" label="招待费" prop="entertainCost"/>
      <el-table-column align="center" label="差旅费" prop="businessTripsCost"/>
      <el-table-column align="center" label="劳务费" prop="laborCost"/>
      <el-table-column align="center" label="其他" prop="otherCost"/>
      <el-table-column align="center" label="项目净利润" prop="projectNetProfit"/>
      <el-table-column align="center" label="项目净利润率" prop="projectNetMargin"/>
      <el-table-column align="center" label="预估收入" prop="preIncome"/>
      <el-table-column align="center" label="预估合同净利润率" prop="preContractProfitRate"/>
      <el-table-column align="center" label="预估成本费用总额" prop="preTotalCost"/>
      <el-table-column align="center" label="实际发生成本费用" prop="actualCostsIncurred"/>
      <el-table-column align="center" label="完工百分比" prop="percentComplete"/>
      <el-table-column align="center" label="需暂估收入" prop="provisionalIncomeRequired"/>
      <el-table-column align="center" label="需暂估成本" prop="provisionalCostRequired"/>
      <el-table-column align="center" label="已暂估成本" prop="provisionalCost"/>
      <el-table-column align="center" label="现金流" prop="cashFlow"/>
      <el-table-column align="center" label="公共费用" prop="publicCost"/>
      <el-table-column align="center" label="公共流出" prop="publicOutflow"/>
      <el-table-column align="center" label="外委付款比例" prop="outsourcePaymentRate"/>
      <el-table-column align="center" label="付款比例-回款比例" prop="paymentReturn"/>
      <el-table-column align="center" label="同比例应付外委款项" prop="sameRateOutsourcePayment"/>
      <el-table-column align="center" label="外委未开票金额" prop="outsourceNotInvoiceAmount"/>
      <el-table-column align="center" label="毛利" prop="grossProfit"/>
      <el-table-column align="center" label="净利" prop="netIncome"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template #default="scope">
          <el-tooltip content="修改" placement="top">
            <el-button v-hasPermi="['oa:oaContractData:edit']" icon="Edit" link type="primary" @click="handleUpdate(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button v-hasPermi="['oa:oaContractData:remove']" icon="Delete" link type="primary" @click="handleDelete(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup name="ElIndex">
const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  },]
</script>

<style scoped>

</style>