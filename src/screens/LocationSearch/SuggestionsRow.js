import { View, Text, Pressable } from 'react-native'
import Entypo from "react-native-vector-icons/Entypo"
import { useNavigation } from '@react-navigation/native'

import styles from './styles'

const SuggestionsRow = ({item}) => {
    const navigation = useNavigation()
    return (
        <Pressable onPress={() =>navigation.navigate("Accommodation Details")}
            style={styles.row}>
            <View style={styles.iconContainer}>
                <Entypo name={"location-pin"} size={35} />
            </View>
            <Text style={styles.locationText}>{item.description}</Text>                            
        </Pressable> 
    )    
}

export default SuggestionsRow
