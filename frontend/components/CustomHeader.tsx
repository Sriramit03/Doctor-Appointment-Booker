import { View, Text } from 'react-native'
import React from 'react'

const CustomHeader = ({title}) => {
  return (
    <View>
      <Text className='text-primaryLight text-2xl font-pbold p-4'>{title}</Text>
    </View>
  )
}

export default CustomHeader