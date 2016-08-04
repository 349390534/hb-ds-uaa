<#import "/console.ftl" as con />
<#import "/util.ftl" as util />
<#if colllist??>
<table class="det-d-qsmx" style="table-layout:fixed ;" id="fundTypeDetailTab">
	<thead id="fundDetailThead">
		<tr class="tit">

			<td class="wdqd">基金类型</td>
			<#list theadlist as head>
				<#if wd == '1' && head != '-1'>
					<td head="${head!}"><a href="javascript:void(0)" class="sort " target="self">${con.opennorms[head!]!}</a></td>
				</#if>
				<#if wd == '2' && head != '-1'>
					<td head="${head!}"><a href="javascript:void(0)" class="sort " target="self">${con.tradenorms[head!]!}</a></td>
				</#if>
			</#list>
		</tr>
		<tr>
		    <td>全部</td>
			<#list theadlist as head>
				<#if head != '-1'>
				<td style="text-align:right;" head="${head!}">
				<#if (coll[con.normsum[head]])??>
					<@util.formatData head coll[con.normsum[head]]/>
				</#if>
				</td>
				</#if>
			</#list>			
		</tr>
	</thead>
	
	<tbody>
		<#list colllist as col>
			<tr>
				<td>${col.fundtypename!}</td>
				<#list theadlist as head>
				<#if head != '-1'>
				<td style="text-align:right;" head="${head!}">
				<#if (col[con.normsum[head]])??>
				<@util.formatData head col[con.normsum[head]]/>
				</#if>
				</td>
				</#if>
				</#list>
			</tr>
		</#list>
	</tbody>														
</table>
</#if>

<script type="text/javascript">
	var wd =$("#fenxiweidu li.current").index()+1;
	checkClickQuota(wd,"3");
</script>