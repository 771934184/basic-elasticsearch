	<select id="selectRelationPkOrg" parameterType="java.util.Map" resultMap="relationMap" >
		select
		tso.pk_org pkOrg ,tso.s_name name
		from tp_sys_org tso
		<where>
			<if test="list != null and list.size > 0">
				tso.pk_org in
				<foreach collection="list" separator="," open="(" close=")" item="item">
					#{item}
				</foreach>
			</if>
		</where>
	</select>
	<resultMap id="relationMap" type="com.cntaiping.dataplatform.common.sysorg.model.TpSysOrgPaC">
		<id column="pkOrg" property="pkOrg"></id>
		<collection property="children" ofType="com.cntaiping.dataplatform.common.sysorg.model.TpSysOrgPaC" column="pkOrg" select="findTpsysOrgByParOrg">
		</collection>
	</resultMap>

	<select id="findTpsysOrgByParOrg" resultMap="relationMap">
		select pk_org pkOrg,s_name name
		from tp_sys_org tso
		where tso.par_org = #{pkOrg}
	</select>
