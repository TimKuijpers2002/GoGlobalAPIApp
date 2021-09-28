import { useState, useEffect } from 'react';
import {getAllAdmins} from "./admin.js";
import {Layout, Menu, Breadcrumb, Table, Spin, Empty} from 'antd';
import {
  DesktopOutlined,
  PieChartOutlined,
  FileOutlined,
  TeamOutlined,
  UserOutlined,
  LoadingOutlined,
} from '@ant-design/icons';

import './App.css';

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;

const columns = [
  {
    title: 'Id',
    dataIndex: 'adminId',
    key: 'adminId',
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Email',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: 'Password',
    dataIndex: 'password',
    key: 'password',
  },
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function App() {
  const [admins, setAdmins] = useState([]);
  const [collapsed, setCollapsed] = useState(false);
  const [fetching, setFetching] = useState(true);

  const fetchAdmins = () =>
      getAllAdmins()
          .then(res => res.json())
          .then(data => {
            console.log(data);
            setAdmins(data);
            setFetching(false);
          })

  useEffect(() => {
    console.log("component is mounted");
    fetchAdmins();
  }, []);

  const renderAdmins = () => {
    if(fetching){
      return <Spin indicator={antIcon} />
    }
    if(admins.length <= 0){
      return <Empty />;
    }
    return <Table
        dataSource={admins}
        columns={columns}
        bordered
        title={() => 'Admins'}
        pagination={{ pageSize: 50 }}
        scroll={{ y: 240 }}
        rowKey = {(admin) => admin.id}
    />;

  }

  return <Layout style={{ minHeight: '100vh' }}>
    <Sider collapsible collapsed={collapsed}
           onCollapse={setCollapsed}>
      <div className="logo" />
      <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
        <Menu.Item key="1" icon={<PieChartOutlined />}>
          Admins
        </Menu.Item>
        <Menu.Item key="2" icon={<PieChartOutlined />}>
          Location forms
        </Menu.Item>
        <Menu.Item key="3" icon={<DesktopOutlined />}>
          Reports
        </Menu.Item>
        <SubMenu key="sub1" icon={<UserOutlined />} title="Current Admin">
        </SubMenu>
        <SubMenu key="sub2" icon={<TeamOutlined />} title="Team">
          <Menu.Item key="6">Team Location</Menu.Item>
          <Menu.Item key="8">Team Reports</Menu.Item>
          <Menu.Item key="8">No Team Assigned</Menu.Item>
        </SubMenu>
      </Menu>
    </Sider>
    <Layout className="site-layout">
      <Header className="site-layout-background" style={{ padding: 0 }} />
      <Content style={{ margin: '0 16px' }}>
        <Breadcrumb style={{ margin: '16px 0' }}>
          <Breadcrumb.Item>Admins</Breadcrumb.Item>
        </Breadcrumb>
        <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
          {renderAdmins()}
        </div>
      </Content>
      <Footer style={{ textAlign: 'center' }}>By Tim Kuijpers ©2021</Footer>
    </Layout>
  </Layout>
}

export default App;
