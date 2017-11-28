import React from 'react'
import PropTypes from 'prop-types'
import { Table, Tag } from 'antd'
import { color } from 'utils'
import styles from './recentSales.less'

const status = {
  1: {
    color: color.green,
    text: 'SALE',
  },
  2: {
    color: color.yellow,
    text: 'REJECT',
  },
  3: {
    color: color.red,
    text: 'TAX',
  },
  4: {
    color: color.blue,
    text: 'EXTENDED',
  },
}

function RecentSales ({ data }) {
  const columns = [
    {
      title: '设备编号',
      dataIndex: 'name',
    }, {
      title: '注册时间',
      dataIndex: 'date',
      render: text => new Date(text).format('yyyy-MM-dd'),
    }, {
      title: '状态',
      dataIndex: 'status',
      render: text => <Tag color={status[text].color}>{status[text].text}</Tag>,
    }, {
      title: '详情',
      dataIndex: 'price',
      render: (text, it) => <span style={{ color: status[it.status].color }}>${text}</span>,
    },
  ]
  return (
    <div className={styles.recentsales}>
      <Table pagination={false} columns={columns} rowKey={(record, key) => key} dataSource={data.filter((item, key) => key < 5)} />
    </div>
  )
}

RecentSales.propTypes = {
  data: PropTypes.array,
}

export default RecentSales
