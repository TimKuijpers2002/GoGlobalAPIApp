import React, {useState} from "react";
import {Breadcrumb, Layout, Empty, Menu} from "antd";
import {EnvironmentOutlined, InboxOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons";
import Sider from "antd/es/layout/Sider";
const {Content, Footer, Header} = Layout;

export default function Home() {
    const [collapsed, setCollapsed] = useState(false);
    return (
        <Layout style={{ minHeight: '100vh' }}>
            <Sider collapsible collapsed={collapsed}
                   onCollapse={setCollapsed}>
                <div className="logo" />
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
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                    </Breadcrumb>
                    <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
                        Hello, welcome to work!
                    </div>
                </Content>
                <Footer style={{ textAlign: 'center' }}>By Tim Kuijpers ©2021</Footer>
            </Layout>
        </Layout>
    );
}