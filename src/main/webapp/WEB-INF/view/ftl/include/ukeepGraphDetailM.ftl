<#import "/util.ftl" as util />
<div class="title">
	<h3 id="exp"></h3>
</div>
<div class="chu_app clearfix">
	<div class="detail1">
		<div class="tab_list">
			<ul class="tab_menu2 clearfix" id="tabm">
				<li class="current">单月数据</li>
				<li>多月对比</li>
			</ul>
			<div class="tab_box">
			<div class="con" style="height: 450px;" tag="chart">
					<div class="yh_select clearfix">
					  <p class="fl">月份</p>
					  <ul class="filter_c fl">
							<li>
								<select name="chart_month_from" id="chart_month_from" class="bar1"></select>
							</li>
						</ul>
						<p class="fl"><xzjr id="xzjr_rs">新增交易人数：0<xzjy></p>
					</div>
					<div class="detail_filter" style="border-bottom: none;">
				    	<div class="charts" id="monthChart">no data</div>
					</div>	
        </div>
        <div class="con hide" style="height: 450px;"  tag="chart">
        	<div class="yh_select clearfix">
        		<p class="fl">指标</p>
					  <ul class="filter_c fl">
							<li>
								<select name="_chart_select" id="_chart_select" class="bar1">
									<#if wd?? && wd=="newuser-m"><option value="dyxzjy_rs">新增交易人数</option></#if>
									<option value="dyfg_rs">复购人数</option>
									<option value="dycl_rs">存量人数</option>
									<option value="dyhl_rs">新增复购人数</option>
								</select>
							</li>
						</ul>
        	</div>
					<div class="detail_filter" style="border-bottom: none;">
					    <div class="charts" id="mutilpartMonthChart">no data</div>
					</div> 
        </div>                                                                             
        </div>
      </div>
</div>
</div>
<div class="detail1">
	<div class="tab_list">
	<div class="tab_box">
		<div class="con">
			<div class="detail_filter1">
			    <div class="zhl4 clearfix">
			        <p class="title">指标：</p>
			        <div class="zhl_right clearfix" id="mutil_month_quota">
					    <div class="ch_left2">
					        <label><input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" value="-1">全部</label>
					    </div>
						<ul class="clearfix">
							<#if wd?? && wd=="newuser-m">
							<li><label><input type="checkbox" name="dykh_zrs" value="dykh_zrs">新开户人数</label></li>
							<li><label><input type="checkbox" name="dyxzjy_rs" value="dyxzjy_rs" pc />新增交易</label></li>
							</#if>
							<#if wd?? && wd=="newtrade-m">
							<li><label><input type="checkbox" name="dyxzjy_rs" value="dyxzjy_rs">新增交易人数</label></li>
							</#if>
							<li><label><input type="checkbox" name="dyfg_rs" value="dyfg_rs" pc />复购</label></li>
							<li><label><input type="checkbox" name="dycl_rs" value="dycl_rs" pc />存量</label></li>
							<li><label><input type="checkbox" name="dyhl_rs" value="dyhl_rs" pc />新增复购</label></li>
						</ul>
			    </div>
    </div>
    <div class="zhl4 clearfix" style="margin-top: 0;">
        <p class="title">维度：</p>
        <div class="zhl_right clearfix" id="mutil_month_wd">
        	<div class="ch_left2">
				<label><input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" value="rs">人数</label>
			</div>
			<ul class="clearfix">
				<li><label><input type="checkbox" value="zb">占比</label></li>
				<li><label><input type="checkbox" value="amt">金额</label></li>
			</ul>
		</div>
    </div>
			</div>
			<div class="zb_table">
				<table id="zb_table">
				  <tbody>
				  <tr tag="title">
				    <td rowspan="3">开户月份</td>
				    <#if wd?? && wd=="newuser-m">
				    <td rowspan="3" tag="dykh_zrs">新开户人数</td>
				    </#if>
				    <#if wd?? && wd=="newtrade-m">
				    <td rowspan="3" tag="dyxzjy_rs">新增交易人数</td>
				    </#if>
				    <td tag="1" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>当月</td>
				    <td tag="2" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第二月</td>
				    <td tag="3" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第三月</td>
				    <td tag="4" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第四月</td>
				    <td tag="5" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第五月</td>
				    <td tag="6" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第六月</td>
				    <td tag="7" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第七月</td>
				    <td tag="8" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第八月</td>
				    <td tag="9" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第九月</td>
				    <td tag="10" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第十月</td>
				    <td tag="11" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第十一月</td>
				    <td tag="12" <#if wd?? && wd=="newtrade-m">colspan="9"<#else>colspan="12"</#if>>第十二月</td>
				  </tr>
				  <tr tag="title2">
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				    
				  	<#if wd?? && wd=="newuser-m">
				    <td colspan="3" tag="dyxzjy_rs">新增交易</td>
				    </#if>
				    <td colspan="3" tag="dyfg_rs">复购</td>
				    <td colspan="3" tag="dycl_rs">存量</td>
				    <td colspan="3" tag="dyhl_rs">新增复购</td>
				  </tr>
				  <tr tag="titled">
				 	<#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_amt">金额</td>
				    
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_rs">人数</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_zb">占比</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_amt">金额</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_rs">人数</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_zb">占比</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_amt">金额</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_rs">人数</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_zb">占比</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_amt">金额</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_rs">人数</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_zb">占比</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_amt">金额</td>
				  </tr>
				  <#if dataList?? && dataList?size gt 0>
				  <#list dataList as dl>
				  <tr tag="titled">
				    <td>${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
				    <#if wd?? && wd=="newuser-m">
				    <td qtag="dykh_zrs">${util.formatNum(dl.dykh_zrs!0)}</td>
				    </#if>
				    <#if wd?? && wd=="newtrade-m">
				    <td qtag="dyxzjy_rs">${util.formatNum(dl.dyxzjy_rs!0)}</td>
				    </#if>
				     <!--当月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_rs">${util.formatNum(dl.dyxzjy_rs!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_zb">${util.formatNum(dl.dyxzjy_rate!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_amt">${util.formatNum(dl.dyxzjy_amt!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_rs">${util.formatNum(dl.dyfg_rs!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_zb">${util.formatNum(dl.dyfg_rate!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_amt">${util.formatNum(dl.dyfg_amt!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_rs">${util.formatNum(dl.dycl_rs!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_zb">${util.formatNum(dl.dycl_rate!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_amt">${util.formatNum(dl.dycl_amt!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_rs">${util.formatNum(dl.dyhl_rs!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_zb">${util.formatNum(dl.dyhl_rate!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_amt">${util.formatNum(dl.dyhl_amt!0)}</td>
				    <!--第二月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_rs">${util.formatNum(dl.dyxzjy_rs_2nd!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_zb">${util.formatNum(dl.dyxzjy_rate_2nd!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_2nd_amt">${util.formatNum(dl.dyxzjy_amt_2nd!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_rs">${util.formatNum(dl.dyfg_rs_2nd!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_zb">${util.formatNum(dl.dyfg_rate_2nd!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_2nd_amt">${util.formatNum(dl.dyfg_amt_2nd!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_rs">${util.formatNum(dl.dycl_rs_2nd!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_zb">${util.formatNum(dl.dycl_rate_2nd!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_2nd_amt">${util.formatNum(dl.dycl_amt_2nd!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_rs">${util.formatNum(dl.dyhl_rs_2nd!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_zb">${util.formatNum(dl.dyhl_rate_2nd!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_2nd_amt">${util.formatNum(dl.dyhl_amt_2nd!0)}</td>
				    <!--第三月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_rs">${util.formatNum(dl.dyxzjy_rs_3rd!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_zb">${util.formatNum(dl.dyxzjy_rate_3rd!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_3rd_amt">${util.formatNum(dl.dyxzjy_amt_3rd!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_rs">${util.formatNum(dl.dyfg_rs_3rd!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_zb">${util.formatNum(dl.dyfg_rate_3rd!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_3rd_amt">${util.formatNum(dl.dyfg_amt_3rd!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_rs">${util.formatNum(dl.dycl_rs_3rd!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_zb">${util.formatNum(dl.dycl_rate_3rd!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_3rd_amt">${util.formatNum(dl.dycl_amt_3rd!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_rs">${util.formatNum(dl.dyhl_rs_3rd!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_zb">${util.formatNum(dl.dyhl_rate_3rd!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_3rd_amt">${util.formatNum(dl.dyhl_amt_3rd!0)}</td>
				    <!--第四月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_rs">${util.formatNum(dl.dyxzjy_rs_4th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_zb">${util.formatNum(dl.dyxzjy_rate_4th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_4th_amt">${util.formatNum(dl.dyxzjy_amt_4th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_rs">${util.formatNum(dl.dyfg_rs_4th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_zb">${util.formatNum(dl.dyfg_rate_4th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_4th_amt">${util.formatNum(dl.dyfg_amt_4th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_rs">${util.formatNum(dl.dycl_rs_4th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_zb">${util.formatNum(dl.dycl_rate_4th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_4th_amt">${util.formatNum(dl.dycl_amt_4th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_rs">${util.formatNum(dl.dyhl_rs_4th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_zb">${util.formatNum(dl.dyhl_rate_4th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_4th_amt">${util.formatNum(dl.dyhl_amt_4th!0)}</td>
				    <!--第五月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_rs">${util.formatNum(dl.dyxzjy_rs_5th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_zb">${util.formatNum(dl.dyxzjy_rate_5th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_5th_amt">${util.formatNum(dl.dyxzjy_amt_5th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_rs">${util.formatNum(dl.dyfg_rs_5th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_zb">${util.formatNum(dl.dyfg_rate_5th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_5th_amt">${util.formatNum(dl.dyfg_amt_5th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_rs">${util.formatNum(dl.dycl_rs_5th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_zb">${util.formatNum(dl.dycl_rate_5th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_5th_amt">${util.formatNum(dl.dycl_amt_5th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_rs">${util.formatNum(dl.dyhl_rs_5th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_zb">${util.formatNum(dl.dyhl_rate_5th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_5th_amt">${util.formatNum(dl.dyhl_amt_5th!0)}</td>
				    <!--第六月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_rs">${util.formatNum(dl.dyxzjy_rs_6th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_zb">${util.formatNum(dl.dyxzjy_rate_6th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_6th_amt">${util.formatNum(dl.dyxzjy_amt_6th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_rs">${util.formatNum(dl.dyfg_rs_6th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_zb">${util.formatNum(dl.dyfg_rate_6th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_6th_amt">${util.formatNum(dl.dyfg_amt_6th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_rs">${util.formatNum(dl.dycl_rs_6th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_zb">${util.formatNum(dl.dycl_rate_6th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_6th_amt">${util.formatNum(dl.dycl_amt_6th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_rs">${util.formatNum(dl.dyhl_rs_6th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_zb">${util.formatNum(dl.dyhl_rate_6th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_6th_amt">${util.formatNum(dl.dyhl_amt_6th!0)}</td>
				    <!--第七月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_rs">${util.formatNum(dl.dyxzjy_rs_7th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_zb">${util.formatNum(dl.dyxzjy_rate_7th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_7th_amt">${util.formatNum(dl.dyxzjy_amt_7th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_rs">${util.formatNum(dl.dyfg_rs_7th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_zb">${util.formatNum(dl.dyfg_rate_7th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_7th_amt">${util.formatNum(dl.dyfg_amt_7th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_rs">${util.formatNum(dl.dycl_rs_7th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_zb">${util.formatNum(dl.dycl_rate_7th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_7th_amt">${util.formatNum(dl.dycl_amt_7th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_rs">${util.formatNum(dl.dyhl_rs_7th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_zb">${util.formatNum(dl.dyhl_rate_7th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_7th_amt">${util.formatNum(dl.dyhl_amt_7th!0)}</td>
				    <!--第八月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_rs">${util.formatNum(dl.dyxzjy_rs_8th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_zb">${util.formatNum(dl.dyxzjy_rate_8th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_8th_amt">${util.formatNum(dl.dyxzjy_amt_8th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_rs">${util.formatNum(dl.dyfg_rs_8th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_zb">${util.formatNum(dl.dyfg_rate_8th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_8th_amt">${util.formatNum(dl.dyfg_amt_8th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_rs">${util.formatNum(dl.dycl_rs_8th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_zb">${util.formatNum(dl.dycl_rate_8th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_8th_amt">${util.formatNum(dl.dycl_amt_8th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_rs">${util.formatNum(dl.dyhl_rs_8th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_zb">${util.formatNum(dl.dyhl_rate_8th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_8th_amt">${util.formatNum(dl.dyhl_amt_8th!0)}</td>
				    <!--第九月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_rs">${util.formatNum(dl.dyxzjy_rs_9th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_zb">${util.formatNum(dl.dyxzjy_rate_9th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_9th_amt">${util.formatNum(dl.dyxzjy_amt_9th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_rs">${util.formatNum(dl.dyfg_rs_9th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_zb">${util.formatNum(dl.dyfg_rate_9th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_9th_amt">${util.formatNum(dl.dyfg_amt_9th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_rs">${util.formatNum(dl.dycl_rs_9th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_zb">${util.formatNum(dl.dycl_rate_9th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_9th_amt">${util.formatNum(dl.dycl_amt_9th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_rs">${util.formatNum(dl.dyhl_rs_9th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_zb">${util.formatNum(dl.dyhl_rate_9th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_9th_amt">${util.formatNum(dl.dyhl_amt_9th!0)}</td>
				    <!--第十月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_rs">${util.formatNum(dl.dyxzjy_rs_10th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_zb">${util.formatNum(dl.dyxzjy_rate_10th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_10th_amt">${util.formatNum(dl.dyxzjy_amt_10th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_rs">${util.formatNum(dl.dyfg_rs_10th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_zb">${util.formatNum(dl.dyfg_rate_10th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_10th_amt">${util.formatNum(dl.dyfg_amt_10th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_rs">${util.formatNum(dl.dycl_rs_10th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_zb">${util.formatNum(dl.dycl_rate_10th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_10th_amt">${util.formatNum(dl.dycl_amt_10th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_rs">${util.formatNum(dl.dyhl_rs_10th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_zb">${util.formatNum(dl.dyhl_rate_10th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_10th_amt">${util.formatNum(dl.dyhl_amt_10th!0)}</td>
				    <!--第十一月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_rs">${util.formatNum(dl.dyxzjy_rs_11th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_zb">${util.formatNum(dl.dyxzjy_rate_11th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_11th_amt">${util.formatNum(dl.dyxzjy_amt_11th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_rs">${util.formatNum(dl.dyfg_rs_11th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_zb">${util.formatNum(dl.dyfg_rate_11th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_11th_amt">${util.formatNum(dl.dyfg_amt_11th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_rs">${util.formatNum(dl.dycl_rs_11th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_zb">${util.formatNum(dl.dycl_rate_11th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_11th_amt">${util.formatNum(dl.dycl_amt_11th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_rs">${util.formatNum(dl.dyhl_rs_11th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_zb">${util.formatNum(dl.dyhl_rate_11th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_11th_amt">${util.formatNum(dl.dyhl_amt_11th!0)}</td>
				    <!--第十二月--->
				    <#if wd?? && wd=="newuser-m">
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_rs">${util.formatNum(dl.dyxzjy_rs_12th!0)}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_zb">${util.formatNum(dl.dyxzjy_rate_12th!0,',##0.00%')}</td>
				    <td ptag="dyxzjy_rs" qtag="dyxzjy_rs_12th_amt">${util.formatNum(dl.dyxzjy_amt_12th!0)}</td>
				    </#if>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_rs">${util.formatNum(dl.dyfg_rs_12th!0)}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_zb">${util.formatNum(dl.dyfg_rate_12th!0,',##0.00%')}</td>
				    <td ptag="dyfg_rs" qtag="dyfg_rs_12th_amt">${util.formatNum(dl.dyfg_amt_12th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_rs">${util.formatNum(dl.dycl_rs_12th!0)}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_zb">${util.formatNum(dl.dycl_rate_12th!0,',##0.00%')}</td>
				    <td ptag="dycl_rs" qtag="dycl_rs_12th_amt">${util.formatNum(dl.dycl_amt_12th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_rs">${util.formatNum(dl.dyhl_rs_12th!0)}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_zb">${util.formatNum(dl.dyhl_rate_12th!0,',##0.00%')}</td>
				    <td ptag="dyhl_rs" qtag="dyhl_rs_12th_amt">${util.formatNum(dl.dyhl_amt_12th!0)}</td>
				  </tr>	
				  </#list>
				  </#if>
				</tbody>
				</table>
			</div>
			<div class="tools fr">
				<input type="submit" value="导出明细" class="btn-style-a">
			</div>
		</div>

	</div>
</div>
</div>
<#include "../include/ukeep_quota_explain_m.ftl">
			
<script type="text/javascript">
$(function(){
	var jsonData =[];
	<#if jsonData??>
		jsonData = ${jsonData!};
	</#if>
	<#if wd??>
		currentWd="${wd!}";
	</#if>
	var wd =$(".fwqd li.current").attr("wd");
	//加载查询条件
	loadQueryExp();
	//绑定tab切换事件
	tabToggleEventM(jsonData.list);
	var month=$("#monthSelect").val();
	drawMonth(jsonData.list,month);
	//初始化图表月份下拉框
	var startM = ${startM!};
	initSelectMonth(startM);
	//图表下拉框月份改变
	eventChartMonthSelect(jsonData);
	//多月指标切换
	eventChartSelect(jsonData.list);
	//复选框选中
	checkBoxDefalutM();
	//复选框事件
	eventCheckboxM();
});
	
</script>