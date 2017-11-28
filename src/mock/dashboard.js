import { color } from '../utils/theme'

const Mock = require('mockjs')
const config = require('../utils/config')

const { apiPrefix } = config

const Dashboard = Mock.mock({
  'completed|12': [
    {
      'name|+1': 2008,
      'Task complete|200-1000': 1,
      'Cards Complete|200-1000': 1,
    },
  ],
  'comments|5': [
    {
      name: '@last',
      'status|1-3': 1,
      content: '@sentence',
      avatar () {
        return Mock.Random.image('48x48', Mock.Random.color(), '#757575', 'png', this.name.substr(0, 1))
      },
      date () {
        return `2016-${Mock.Random.date('MM-dd')} ${Mock.Random.time('HH:mm:ss')}`
      },
    },
  ],
  'recentSales|36': [
    {
      'id|+1': 1,
      name: '@last',
      'status|1-4': 1,
      date () {
        return `${Mock.Random.integer(2015, 2016)}-${Mock.Random.date('MM-dd')} ${Mock.Random.time('HH:mm:ss')}`
      },
      'price|10-200.1-2': 1,
    },
  ],
  numbers: [
    {
      icon: '',
      color: color.green,
      title: 'SIM卡警情',
      number: 10,
    }, {
      icon: 'num',
      color: color.blue,
      title: '追踪器数量',
      number: 100,
    }, {
      icon: '',
      color: color.purple,
      title: '哨位数量',
      number: 30,
    }, {
      icon: '',
      color: color.red,
      title: '设备异常警情',
      number: 110,
    },
    {
      icon: '',
      color: color.red,
      title: '警情',
      number: 220,
    },
  ],
})

module.exports = {
  [`GET ${apiPrefix}/dashboard`] (req, res) {
    res.json(Dashboard)
  },
}
