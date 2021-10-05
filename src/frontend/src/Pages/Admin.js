import React, {useEffect, useState} from "react";
import {Breadcrumb, Empty, Layout, Menu, Spin, Table} from "antd";
import {EnvironmentOutlined, InboxOutlined, LoadingOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import {getAllAdmins} from "../Models/admin";
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
export default function Admin() {
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
    return (
        <Layout style={{ minHeight: '100vh' }}>
            <Sider collapsible collapsed={collapsed}
                   onCollapse={setCollapsed}>
                <div className="./logo" />
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                    <Menu.Item key="1" icon={<TeamOutlined />} onClick={() => {window.location.pathname = "/Admin"}}>
                        Admins
                    </Menu.Item>
                    <Menu.Item key="2" icon={<InboxOutlined />} onClick={() => {window.location.pathname = "/LocationSubmit"}}>
                        Location submits
                    </Menu.Item>
                    <Menu.Item key="3" icon={<InboxOutlined />} onClick={() => {window.location.pathname = "/Report"}}>
                        Reports
                    </Menu.Item>
                    <Menu.Item key="4" icon={<UserOutlined />} onClick={() => {window.location.pathname = "/Profile"}}>
                        Profile
                    </Menu.Item>
                    <Menu.Item key="5" icon={<EnvironmentOutlined />} onClick={() => {window.location.pathname = "/Location"}}>
                        Locations
                    </Menu.Item>
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
    );
}