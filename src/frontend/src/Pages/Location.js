import React, {useEffect, useState} from "react";
import {Sidebar} from "../Components/Sidebar";
import {LoadingOutlined} from "@ant-design/icons";
import axios from 'axios';
import {Empty, Spin, Table, Tag} from "antd";
import Text from "antd/es/typography/Text";

const columns = [
    {
        title: 'Id',
        dataIndex: 'locationId',
        key: 'locationId',
        render: text => <Text>{text}</Text>,
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
        render: text => <Text>{text}</Text>,
    },
    {
        title: 'Coordinates',
        dataIndex: 'coordinates',
        key: 'coordinates',
        render: text => <Text>{text}</Text>,
    },
    {
        title: 'General Content',
        dataIndex: 'generalContent',
        key: 'generalContent',
        render: text => <Text>{text}</Text>,
    },
    {
        title: 'Facilities',
        dataIndex: 'facilities',
        key: 'facilities',
        render: facilities => (
            <Text>
                {facilities.map(facility => {
                    let color = 'geekblue';
                    return (
                        <Tag color={color} key={facility.name}>
                            {facility.name.toUpperCase()}
                        </Tag>
                    );
                })}
            </Text>
        ),
    },
    {
        title: 'Terrains',
        dataIndex: 'terrains',
        key: 'terrains',
        render: terrains => (
            <Text>
                {terrains.map(terrain => {
                    let color = 'geekblue';
                    return (
                        <Tag color={color} key={terrain.name}>
                            {terrain.name.toUpperCase()}
                        </Tag>
                    );
                })}
            </Text>
        ),
    },
    {
        title: 'Categories',
        dataIndex: 'categories',
        key: 'categories',
        render: categories => (
            <Text>
                {categories.map(category => {
                    let color = 'geekblue';
                    return (
                        <Tag color={color} key={category.name}>
                            {category.name.toUpperCase()}
                        </Tag>
                    );
                })}
            </Text>
        ),
    },
    {
        title: 'Likes',
        dataIndex: 'likes',
        key: 'likes',
        render: text => <Text>{text}</Text>,
    },
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

export default function Location() {
    const [locations, setLocations] = useState([]);
    const [fetching, setFetching] = useState(true);

    const fetchLocations = () =>
        axios.get("api/locations")
            .then(res => {
                console.log(res);
                setLocations(res.data);
                setFetching(false);
            })

    useEffect(() => {
        console.log("component is mounted");
        fetchLocations().then(r => console.log(r));
    }, []);

    const renderLocations = () => {
        if(fetching){
            return <Spin indicator={antIcon} />
        }
        if(locations.length <= 0){
            return <Empty />;
        }
        return <Table
            dataSource={locations}
            columns={columns}
            bordered
            title={() => 'Locations'}
            pagination={{ pageSize: 50 }}
            scroll={{ y: 240 }}
            rowKey = {(location) => location.id}
        />;

    }
    return (
        <Sidebar data={
            <div>
                {renderLocations()}
            </div>
        }/>
    );
}